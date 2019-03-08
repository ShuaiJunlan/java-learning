package cn.shuaijunlan.design.patterns.thread.threewayscreatethread;

import java.util.concurrent.Callable;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 19:43 2017/4/10.
 */
public class ImplCallable implements Callable<String> {
    public String name;

    public ImplCallable(String name) {
        this.name = name;
    }

    public String call() throws Exception {
        return name;
    }
}
