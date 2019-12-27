package cn.shuaijunlan.jmh.learning.reflection;

import cn.shuaijunlan.jmh.learning.reflection.javabean.JavaBean;
import cn.shuaijunlan.jmh.learning.reflection.javabean.NestedJavaBean;
import jodd.bean.BeanUtil;
import org.apache.commons.beanutils.PropertyUtils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 10:41 AM 12/27/19.
 */
@Fork(3)
@Warmup(iterations = 5, time = 3)
@Measurement(iterations = 5, time = 1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class JavaBeanUtilBenchmark {

    @Param({
            "fieldA",
            "nestedJavaBean.fieldA",
            "nestedJavaBean.nestedJavaBean.fieldA",
            "nestedJavaBean.nestedJavaBean.nestedJavaBean.fieldA"
    })
    String fieldName;
    JavaBean javaBean;

    @Setup
    public void setup() {
        NestedJavaBean nestedJavaBean3 = NestedJavaBean.builder().fieldA("nested-3").build();
        NestedJavaBean nestedJavaBean2 = NestedJavaBean.builder().fieldA("nested-2").nestedJavaBean(nestedJavaBean3).build();
        NestedJavaBean nestedJavaBean1 = NestedJavaBean.builder().fieldA("nested-1").nestedJavaBean(nestedJavaBean2).build();
        javaBean = JavaBean.builder().fieldA("fieldA").nestedJavaBean(nestedJavaBean1).build();
    }

    @Benchmark
    public Object invokeDynamic() {
        return JavaBeanUtil.getFieldValue(javaBean, fieldName);
    }

    /**
     * Reference: http://commons.apache.org/proper/commons-beanutils/
     */
    @Benchmark
    public Object apacheBeanUtils() throws Exception {
        return PropertyUtils.getNestedProperty(javaBean, fieldName);
    }

    /**
     * Reference: https://jodd.org/beanutil/
     */
    @Benchmark
    public Object joddBean() {
        return BeanUtil.declared.getProperty(javaBean, fieldName);
    }

    public static void main(String... args) throws IOException, RunnerException {
        // Main.main(args);
        Options options = new OptionsBuilder()
                .include(JavaBeanUtilBenchmark.class.getSimpleName())
                .resultFormat(ResultFormatType.JSON)
                // .output("Benchmark.json")
                .build();
        new Runner(options).run();
    }
}