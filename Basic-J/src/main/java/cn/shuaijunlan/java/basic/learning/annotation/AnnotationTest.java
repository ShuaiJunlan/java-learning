package cn.shuaijunlan.java.basic.learning.annotation;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 7:39 PM 1/6/19.
 */
@TestClass
public class AnnotationTest {
    @TestMethod(value = "test", name = "hello")
    public void test(){
    }

    public static void main(String[] args) {
        TestClass testClass = AnnotationTest.class.getAnnotation(TestClass.class);
        System.out.println(testClass.value());

        try {
            TestMethod testMethod = AnnotationTest.class.getDeclaredMethod("test", null).getAnnotation(TestMethod.class);
            System.out.println(testMethod.value());
            System.out.println(testMethod.name());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
