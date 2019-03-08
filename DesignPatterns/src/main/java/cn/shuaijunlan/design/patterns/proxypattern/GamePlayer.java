package cn.shuaijunlan.design.patterns.proxypattern;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 18:08 2017/3/6.
 */
public class GamePlayer implements IGamePlayer
{
    String name = "";
    public GamePlayer(String name)
    {
        this.name = name;
    }
    public void login(String username, String password)
    {
        System.out.println("登录名为" + username + "的用户" + this.name + "登录成功");
    }

    public void killBoss()
    {
        System.out.println("用户" + this.name + "在杀怪！");
    }

    public void upgrade()
    {
        System.out.println("用户" + this.name + "升级！");
    }
}
