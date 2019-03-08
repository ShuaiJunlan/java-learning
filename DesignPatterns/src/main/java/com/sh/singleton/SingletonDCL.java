package com.sh.singleton;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 11:23 AM 3/8/19.
 */
public class SingletonDCL {
    private static volatile SingletonDCL singletonDCL = null;
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
