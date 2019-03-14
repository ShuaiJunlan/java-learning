package cn.shuaijunlan.alibaba1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 7:28 PM 3/14/19.
 */
public class Trie {

    /**
     * A-Z : 65-90
     * a-z : 97-122
     *
     * 字典树的节点
     */
    private class TrieNode{
        TrieNode[] children = new TrieNode[52];
        /**
         * 记录当前单词出现的次数
         */
        AtomicLong count = new AtomicLong(0);
    }

    /**
     * 字典树的根节点
     */
    private TrieNode root = new TrieNode();

    /**
     * 存储所有单词统计数据
     */
    private Map<String, Long> statistics = new ConcurrentHashMap<>(128);


    public Trie() {

    }

    private final Object LOCK = new Object();

    /**
     * 插入单词
     * @param word
     */
    public void addWord(String word) {
        if (word == null || word.trim().length() == 0){
            return;
        }
        TrieNode node = root;
        for (char c : word.toCharArray()){
            //算出对应的TrieNode的index
            int index = c > 90 ? (c-'a' + 26) : (c-'A');

            if (node.children[index] == null){
                synchronized (LOCK){
                    if (node.children[index] == null){
                        node.children[index] = new TrieNode();
                    }
                }
            }
            node = node.children[index];
        }
        statistics.put(word, node.count.incrementAndGet());
    }

    /**
     * 获取所有单词的统计信息
     * @return
     */
    public Map getAllWordStatistics(){

        return statistics;
    }

    /**
     * 获取单词出现的次数
     * @param word
     * @return
     */

    public long getWordCount(String word){
        if (word == null || word.trim().length() == 0){
            return 0;
        }
        return statistics.get(word);

    }

    private long match(char[] chs, TrieNode node){
        int i = 0;
        while (i < chs.length){
            char c = chs[i];
            if (c > 90){
                node = node.children[c-'a'+26];
            }else {
                node = node.children[c-'A'];
            }
            if (node == null){
                return 0;
            }
            i++;
        }
        return node.count.get();
    }



    // public void allWordCounts(TrieNode root, HashMap<String, Long> map){
    //     if (root == null){
    //         return;
    //     }
    //     for (int i = 0; i < 52; i++){
    //         if (root.children[i] != null){
    //             long temp = root.children[i].count.get();
    //             if (temp != 0){
    //                 map.put(root.children[i].item, temp);
    //             }
    //             allWordCounts(root.children[i], map);
    //         }
    //     }
    // }
}
