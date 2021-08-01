package SystemDesign;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class TrieNode {
    public boolean isLeaf;
    public List<String> cands;
    HashMap<Character, TrieNode> children;
    public TrieNode() {
        isLeaf = false;
        children = new HashMap<Character, TrieNode>();
        cands = new LinkedList<String>();
    }
}
