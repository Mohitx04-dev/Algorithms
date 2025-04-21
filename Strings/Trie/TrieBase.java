package Strings.Trie;

class TrieNode {
    TrieNode[] arr;
    boolean isLast = false;
    public TrieNode() {
        arr = new TrieNode[26];
        isLast = false;
    }
}
class Trie {
    private final TrieNode root;
    Trie() {
        root = new TrieNode();
    }
    void insert(String s) {
        TrieNode node = root;
        for(char c : s.toCharArray()) {
            if(node.arr[c-'a']==null) node.arr[c-'a'] = new TrieNode();
            node = node.arr[c-'a'];
        }
        node.isLast = true;
    }
    boolean find(String s) {
        TrieNode node = traverse(s);
        return node != null && node.isLast;
    }
    boolean startsWith(String prefix) {
        return traverse(prefix)!=null;
    }
    TrieNode traverse(String s) {
        TrieNode node = root;
        for(char c : s.toCharArray()) {
            if(node.arr[c-'a']!=null) node = node.arr[c-'a'];
            else return null;
        }
        return node;
    }
    boolean delete(String word) {
        return delete(root, word, 0);
    }
    private boolean delete(TrieNode node, String word, int index) {
        if(index==word.length()) {
            if(!node.isLast) return false;
            node.isLast = false;
            return isEmpty(node);
        }
        char tbd = word.charAt(index);
        TrieNode next = node.arr[tbd-'a'];
        if(next==null) return false;
        boolean shouldDeleteChild  = delete(next,word,index+1);
        if(shouldDeleteChild ) {
            node.arr[tbd-'a'] = null;
            return !node.isLast && isEmpty(node);
        }
        return false;
    }
    private boolean isEmpty(TrieNode node) {
        for (int i = 0; i < 26; i++) {
            if (node.arr[i] != null) return false;
        }
        return true;
    }
}

public class TrieBase { 
    public static void main(String[] args) {
        String s1 = "thalaisgood";
        String s2 = "thalaisamazing";
        String s3 = "thala";
        String s4 = "good";
        Trie trie = new Trie();
        trie.insert(s1);
        trie.insert(s2);
        System.out.println(trie.startsWith(s3));
        System.out.println(trie.find(s4));
        System.out.println(trie.find(s1));
        System.out.println(trie.delete(s1));
        System.out.println(trie.find(s1));
    }
}