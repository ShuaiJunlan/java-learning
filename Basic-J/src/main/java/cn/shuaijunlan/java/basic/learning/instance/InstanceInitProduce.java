package cn.shuaijunlan.java.basic.learning.instance;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:47 AM 2/25/19.
 */
public class InstanceInitProduce {
    public static InstanceInitProduce t1 = new InstanceInitProduce();
    public static InstanceInitProduce t2 = new InstanceInitProduce();
    {
        System.out.println("构造块");
    }
    static
    {
        System.out.println("静态块");
    }
    public static void main(String[] args)
    {
        InstanceInitProduce t = new InstanceInitProduce();
    }
}
