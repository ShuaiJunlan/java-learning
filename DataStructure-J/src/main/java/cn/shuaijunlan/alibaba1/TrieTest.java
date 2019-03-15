package cn.shuaijunlan.alibaba1;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 8:07 PM 3/14/19.
 */
public class TrieTest {
    public static void main(String[] args) {
        Trie trie = new Trie();
        String[] strings = new String[]{"", "D", "wewe", "dfadsf", "SDFdsfgsdf", "sdfgartFFffGSDFG", "SDFdsfgsdf", "asdfqerawerqwefasdfasdtqwer", "SDFGADUHTIERUHsdfgjnisudfgSJKFDHGJSDFG"};
        for (String s : strings){
            trie.addWord(s);
        }
        for (String s : strings){
            System.out.println(trie.getWordCount(s));
        }
        System.out.println(trie.getAllWordStatistics().toString());
    }
}
