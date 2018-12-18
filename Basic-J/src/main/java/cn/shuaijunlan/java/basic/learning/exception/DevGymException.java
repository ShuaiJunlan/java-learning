package cn.shuaijunlan.java.basic.learning.exception;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 10:01 PM 12/18/18.
 */
public class DevGymException {
    private static IllegalArgumentException illegalArgumentException;

    public static void main(String[] args) {
        try {
            double exceptionNUmber = 2 / 0.0;
            // int exceptionNUmber = 2 / 0; // What will be printed?
            System.out.println("exceptionNumber");
        }catch (NullPointerException e){
            System.out.println("NullPointer");
        }catch (IllegalArgumentException e){
            System.out.println("IllegalArgument");
        }catch (Exception e){
            System.out.println("Exception");
        }finally {
            System.out.println("Finally");
        }
    }
}
