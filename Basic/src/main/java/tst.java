/**
 * Created by Mr SJL on 2016/12/19.
 *
 * @Author Junlan Shuai
 */
public class tst
{
    static class OOMObject
    {

    }

    public static void main(String[] args)
    {
//        List<OOMObject> list = new ArrayList<OOMObject>();
//        while (true)
//        {
//            list.add(new OOMObject());
//        }

        String str1  = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}
