
import java.util.ArrayList;
import java.util.List;

public class Solution {

    private static class TrieNode {
        TrieNode[] child = new TrieNode[10];
        String key;
        boolean eof;
    }

    public static class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String key) {
            TrieNode node = this.root;

            for (int i = 0; i < key.length(); i++) {
                int idx = key.charAt(i) - '0';

                if (node.child[idx] == null) {
                    node.child[idx] = new TrieNode();
                }else {
                    node.child[idx].eof = false;
                }

                node = node.child[idx];
            }

            node.key = key;
            node.eof = true;
        }

        public List<String> findByPrefix(String key) {
            List<String> res = new ArrayList<>();

            TrieNode node = this.root;
            for (int i = 0; i < key.length(); i++) {
                int idx = key.charAt(i) - '0';

                if (node.child[idx] == null) {
                    return res;
                }

                node = node.child[idx];
            }

            findByPrefix(node, res);

            return res;
        }

        private void findByPrefix(TrieNode node, List<String> res) {
            if (node.eof || node.key != null) {
                res.add(node.key);
            }

            for (TrieNode child : node.child) {
                if (child != null) {
                    findByPrefix(child, res);
                }
            }
        }
    }


    public static boolean solution(String[] phone_book) {
        Trie trie = new Trie();

        for (String s : phone_book) {
            trie.insert(s);
        }

        for (String s : phone_book) {
            List<String> prefix = trie.findByPrefix(s);
            if (prefix.size() > 1) {
                return false;
            }
        }

        return true;
    }
}
