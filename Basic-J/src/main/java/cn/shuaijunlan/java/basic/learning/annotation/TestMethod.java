package cn.shuaijunlan.java.basic.learning.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 7:37 PM 1/6/19.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TestMethod {
    String value();
    String name();
}
