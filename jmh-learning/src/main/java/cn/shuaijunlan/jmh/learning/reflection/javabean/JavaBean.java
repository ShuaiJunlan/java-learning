package cn.shuaijunlan.jmh.learning.reflection.javabean;

import lombok.Builder;
import lombok.Value;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 10:37 AM 12/27/19.
 */
@Value
@Builder
public class JavaBean {
    private final String fieldA;
    private final NestedJavaBean nestedJavaBean;
}
