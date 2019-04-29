package cn.shuaijunlan.java.basic.learning.trycatch;

import org.junit.Test;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 3:31 PM 2018/09/17.
 */
public class TryCatchTest {

    /**
     * Testing return and System.exit()
     */
    @Test
    public void test1(){
        // try {
        //     System.out.println(1);
        //     return;
        // }finally {
        //     System.out.println(2);
        // }

        try {
            System.out.println(1);
            System.exit(1);
        }finally {
            System.out.println(2);
        }
        //case 1
        try {
            System.out.println("try");
        }catch (Exception e){
            System.out.println("catch");
        }finally {
            System.out.println("finally");
        }

        //case 2
        try {
            System.out.println("try");
        }finally {
            System.out.println("finally");
        }

        //case 3
        try {
            System.out.println("try");
        }catch (Exception e){
            System.out.println("catch");
        }

    }

    /**
     * Testing try-catch returning value
     * If there is a return statement in finally block, it will execute,
     * If there isn't a return statement in finally block, and there isn't exception in try block, the value will be returned in try block,
     * others, the value will be returned in catch block.
     */
    @Test
    public void test2(){
        System.out.println(getValue());
        System.out.println(getValue11());
    }

    public int getValue(){
        try {
            int b = 1 / 0;
            return 1;
        }catch (Exception e){
            return 2;
        }finally {
            return 3;
        }
    }

    public int getValue11(){
        try {
            return 1;
        }catch (Exception e){
            return 2;
        }finally {
            return 3;
        }
    }

    @Test
    public void test3(){
        System.out.println(getValueWhenException());
        System.out.println(getValueWhenException1());
        System.out.println(getValue1());
    }

    /**
     * When it occurs exception or not, the return statement in the finally block will be executed;
     * @return value
     */
    private int getValueWhenException(){
        try {
            int i = 1 / 0;
            return 1;
        }catch (Exception e){
            return 2;
        }finally {
            return 3;
        }
    }

    /**
     * If there is not finally block, when getting exception, the return statement in the catch block will be executed,
     * otherwise the return statement in the try block will be executed.
     * @return value
     */
    private int getValueWhenException1(){
        try {
            int i = 1 / 0;
            return 1;
        }catch (Exception e){
            return 2;
        }
    }

    /**
     * What is the return value?
     * @return value
     */
    private int getValue1(){
        int x = 1;
        try {
            return ++x;
        }catch (Exception e){

        }finally {
            ++x;
        }
        System.out.println("hh");
        return x;
    }
}
