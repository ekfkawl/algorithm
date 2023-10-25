
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    static int N;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Trie trie = new Trie();

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split("\\\\");

            String root = s[0];
            String[] childs = Arrays.copyOfRange(s, 1, s.length);

            trie.insert(root, childs);
        }

        Map<String, TrieNode> child = trie.root.child;
        for (TrieNode value : child.values()) {
            trie.print(value, "");
        }


    }

    public static class Trie {
        TrieNode root = new TrieNode();

        public void insert(String root, String[] childs) {
            TrieNode node = this.root.child.computeIfAbsent(root, v -> new TrieNode());
            node.key = root;

            for (String child : childs) {
                node = node.child.computeIfAbsent(child, v -> new TrieNode());
                node.key = child;
            }
        }

        public void print(TrieNode currentNode, String depth) {
            System.out.println(depth + currentNode.key);

            if (currentNode.child.size() == 0) {
                return;
            }

            Map<String, TrieNode> childNode = currentNode.child;
            for (TrieNode nextNode : childNode.values()) {
                print(nextNode, depth + " ");
            }
        }
    }

    public static class TrieNode {
        Map<String, TrieNode> child = new TreeMap<>();
        String key;
    }
}
