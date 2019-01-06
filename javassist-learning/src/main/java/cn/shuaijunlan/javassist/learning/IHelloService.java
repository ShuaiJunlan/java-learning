package cn.shuaijunlan.javassist.learning;

import java.util.List;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 8:14 PM 1/6/19.
 */
public interface IHelloService {
    void say(String msg);

    String echo(String msg);

    String[] getHobbies();

    int insert(User user);

    User getUser();

    List<User> getUser(String group, int age);

}
