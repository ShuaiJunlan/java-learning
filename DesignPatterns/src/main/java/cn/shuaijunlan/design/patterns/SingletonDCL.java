package cn.shuaijunlan.design.patterns;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 10:37 AM 3/8/19.
 */
public class SingletonDCL {
    private volatile static SingletonDCL singletonDCL = null;
    public static SingletonDCL getInstance(){
        if (singletonDCL == null){
            synchronized (SingletonDCL.class){
                if (singletonDCL == null){
                    singletonDCL = new SingletonDCL();
                    return singletonDCL;
                }
            }
        }
        return singletonDCL;
    }
}

