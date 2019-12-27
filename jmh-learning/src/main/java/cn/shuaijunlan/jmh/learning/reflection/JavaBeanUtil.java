package cn.shuaijunlan.jmh.learning.reflection;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.apache.commons.lang3.StringUtils;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaConversionException;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 10:40 AM 12/27/19.
 */
public class JavaBeanUtil {
    private static final Pattern FIELD_SEPARATOR = Pattern.compile("\\.");
    private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
    private static final ClassValue<Map<String, Function>> CACHE = new ClassValue<Map<String, Function>>() {
        @Override
        protected Map<String, Function> computeValue(Class<?> type) {
            return new ConcurrentHashMap<>();
        }
    };

    private JavaBeanUtil() {}

    public static <T> T getFieldValue(Object javaBean, String fieldName) {
        return (T) getCachedFunction(javaBean.getClass(), fieldName).apply(javaBean);
    }

    private static Function getCachedFunction(Class<?> javaBeanClass, String fieldName) {
        final Function function = CACHE.get(javaBeanClass).get(fieldName);
        if (function != null) {
            return function;
        }
        return createAndCacheFunction(javaBeanClass, fieldName);
    }

    private static Function createAndCacheFunction(Class<?> javaBeanClass, String path) {
        return cacheAndGetFunction(path, javaBeanClass,
                createFunctions(javaBeanClass, path)
                        .stream()
                        .reduce(Function::andThen)
                        .orElseThrow(IllegalStateException::new)
        );
    }

    private static Function cacheAndGetFunction(String path, Class<?> javaBeanClass, Function functionToBeCached) {
        Function cachedFunction = CACHE.get(javaBeanClass).putIfAbsent(path, functionToBeCached);
        return cachedFunction != null ? cachedFunction : functionToBeCached;
    }

    private static List<Function> createFunctions(Class<?> javaBeanClass, String path) {
        List<Function> functions = new ArrayList<>();
        Stream.of(FIELD_SEPARATOR.split(path))
                .reduce(javaBeanClass, (nestedJavaBeanClass, fieldName) -> {
                    Tuple2<? extends Class, Function> getFunction = createFunction(fieldName, nestedJavaBeanClass);
                    functions.add(getFunction._2);
                    return getFunction._1;
                }, (previousClass, nextClass) -> nextClass);
        return functions;
    }

    private static Tuple2<? extends Class, Function> createFunction(String fieldName, Class<?> javaBeanClass) {
        return Stream.of(javaBeanClass.getDeclaredMethods())
                .filter(JavaBeanUtil::isGetterMethod)
                .filter(method -> StringUtils.endsWithIgnoreCase(method.getName(), fieldName))
                .map(JavaBeanUtil::createTupleWithReturnTypeAndGetter)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private static boolean isGetterMethod(Method method) {
        return method.getParameterCount() == 0 &&
                !Modifier.isStatic(method.getModifiers()) &&
                method.getName().startsWith("get") &&
                !method.getName().endsWith("Class");
    }

    private static Tuple2<? extends Class, Function> createTupleWithReturnTypeAndGetter(Method getterMethod) {
        try {
            return Tuple.of(
                    getterMethod.getReturnType(),
                    (Function) createCallSite(LOOKUP.unreflect(getterMethod)).getTarget().invokeExact()
            );
        } catch (Throwable e) {
            throw new IllegalArgumentException("Lambda creation failed for getterMethod (" + getterMethod.getName() + ").", e);
        }
    }

    private static CallSite createCallSite(MethodHandle getterMethodHandle) throws LambdaConversionException {
        return LambdaMetafactory.metafactory(LOOKUP, "apply",
                MethodType.methodType(Function.class),
                MethodType.methodType(Object.class, Object.class),
                getterMethodHandle, getterMethodHandle.type());
    }
}
