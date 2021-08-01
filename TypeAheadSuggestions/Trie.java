package SystemDesign;

import java.util.HashMap;

public class Trie {
    private TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0;i < word.length();i++) {
            HashMap<Character, TrieNode> children = node.children;
            char c = word.charAt(i);
            if (!children.containsKey(c)) {
                children.put(c, new TrieNode());
            }
            children.get(c).cands.add(word);
            if (i == word.length() - 1) {
                children.get(c).isLeaf = true;
            }
            node = node.children.get(c);
        }
    }

    public TrieNode searchNode(String pre) {
        HashMap<Character, TrieNode> children = root.children;
        TrieNode node = root;
        for (int i = 0; i < pre.length(); i++) {
            if (!children.containsKey(pre.charAt(i))) {
                return null;
            }
            node = children.get(pre.charAt(i));
            children = node.children;
        }
        return node;
    }
}
