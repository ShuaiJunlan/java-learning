package com.sh.proxypattern;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 18:06 2017/3/6.
 */
public interface IGamePlayer
{
    void login(String username, String password);
    void killBoss();
    void upgrade();
}
