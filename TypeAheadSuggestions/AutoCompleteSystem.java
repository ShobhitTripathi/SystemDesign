package StackAndQueue;

import java.util.*;

class AutocompleteSystem {
    HashMap<String, Integer> count = new HashMap<>();
    Trie trie = new Trie();
    String curr = "";

    public AutocompleteSystem(String[] sentences, int[] times) {
        for (int i = 0; i < sentences.length; i ++) {
            count.put(sentences[i], times[i]);
            trie.insert(sentences[i]);
        }
    }

    public List<String> input(char c) {
        List<String> res = new LinkedList<String>();
        if (c == '#') {
            if (!count.containsKey(curr)) {
                trie.insert(curr);
                count.put(curr, 1);
            }
            else {
                count.put(curr, count.get(curr) + 1);
            }
            curr = "";
        }
        else {
            curr += c;
            res = getSuggestions();
        }

        return res;
    }

    private List<String> getSuggestions () {
        List<String> res = new LinkedList<>();
        TrieNode node = trie.searchNode(curr);
        if (node == null) {
            return res;
        }
        List<String> cands = node.cands;
        Collections.sort(cands, new Comparator<String>() {
            public int compare(String s1, String s2) {
                if (count.get(s1) != count.get(s2)) {
                    return count.get(s2) - count.get(s1);
                }
                return s1.compareTo(s2);
            }
        });
        int added = 0;
        for (String s:cands) {
            res.add(s);
            added ++;
            if (added > 2) {
                break;
            }
        }
        return res;
    }


    /*------------------Driver Code ------------*/
    public static void main(String[] args) {
        //The system have already tracked down the following sentences and their corresponding times
        String[] sentences = {"i love you", "island","ironman", "i love India"};
        int[] times = {5,3,2,2};
        AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
        //Operation: input('i')
        //Output: ["i love you", "island","i love India"]
        List<String> list1 = obj.input('i');
        System.out.println(list1);

        //Operation: input(' ')
        //Output: ["i love you","i love India"]
        List<String> list2 = obj.input(' ');
        System.out.println(list2);

        //Operation: input('a')
        //Output: []
        List<String> list3 = obj.input('a');
        System.out.println(list3);

        //Operation: input('#')
        //Output: []
        List<String> list4 = obj.input('#');
        System.out.println(list4);
    }
}
