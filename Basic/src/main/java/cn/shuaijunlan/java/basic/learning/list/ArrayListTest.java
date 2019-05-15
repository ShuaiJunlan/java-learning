package cn.shuaijunlan.java.basic.learning.list;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:25 AM 5/10/19.
 */
public class ArrayListTest {
    @Test
    public void test(){
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("fdf");
        arrayList.add("eqweqwe");
        arrayList.add("eqweqasdfasdfwe");

        String[] t = arrayList.toArray(new String[0]);

        String[] strings = new String[arrayList.size()];
        arrayList.toArray(strings);

    }
}
