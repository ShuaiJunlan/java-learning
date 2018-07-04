package cn.shuaijunlan.nowcoder.$2017.$04.$26;

import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 20:20 2017/4/26.
 */
/*
阿里的消息中间件，负责淘宝天猫支付宝等各个系统的消息中转，削峰填谷及架构的解耦。在每年的双11中承载了
数万亿的消息。消息中间件中有Pub/Sub两个角色，Pub方发送消息到消息中间件，消息中间件再根据订阅关系投递给订阅方。
例如用户成功购买了一个物品，交易平台（Pub）会发送一条交易完成（trade-done）的消息，购物车平台（Sub）订阅到这个消息后，
会将用户的购物车物品删除掉。这里涉及一个问题，交易平台会发送各种类型的消息，
消息中间件是如何准确的将相应的消息投递给购物车平台的？所使用的就是消息中间件的过滤功能，过滤的方式有很多种，表达式判断，
正则匹配等。假设让你来实现一个过滤功能，来匹配订阅关系是否符合，给定如下条件：

‘?’ 匹配一个字符

‘*’ 匹配任意连串的字符

如上面的例子中，购物车平台订阅方式是pattern=*trade-done，那么

filter(100-trade-done, pattern) = 1,

filter(200-trade-done, pattern) = 1,

filter(200-paid-done, pattern) = 0
 */
public class App2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line1 = scanner.nextLine();
        String line2 = scanner.nextLine();
        System.out.println(isPattern(line2, line1));
    }

    public static int isPattern(String pattern, String str) {
        return 1;
    }
}
