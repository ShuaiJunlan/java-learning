package cn.shuaijunlan.java.basic.learning.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 7:35 PM 1/6/19.
 */
@Target({ElementType.TYPE})     //作用到类
@Retention(RetentionPolicy.RUNTIME) //运行时
public @interface TestClass {
    String value() default "default";
}
