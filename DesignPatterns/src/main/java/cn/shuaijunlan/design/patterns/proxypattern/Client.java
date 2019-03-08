package cn.shuaijunlan.design.patterns.proxypattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 18:14 2017/3/6.
 */
public class Client
{
    public static void main(String[] args)
    {
        IGamePlayer gamePlayer = new GamePlayer("张三");
        InvocationHandler handler = new GamePlayIH(gamePlayer);
        ClassLoader loader =gamePlayer.getClass().getClassLoader();
        IGamePlayer proxy = (IGamePlayer) Proxy.newProxyInstance(loader, new Class[]{IGamePlayer.class}, handler);
        proxy.login("shuaijunlan", "123");
        proxy.killBoss();
        proxy.upgrade();
    }
}
