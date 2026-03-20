import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int T; // 테스트케이스 개수
    static int[] N; // 번호 개수
    static String[][] numbers; // 번호

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력값 셋팅
        T = Integer.parseInt(br.readLine());
        N = new int[T];
        numbers = new String[T][];

        for (int i = 0; i < T; i++) {
            N[i] = Integer.parseInt(br.readLine());
            numbers[i] = new String[N[i]];

            for (int j = 0; j < N[i]; j++) {
                numbers[i][j] = br.readLine();
            }
        }

        // 출력
        for (int i = 0; i < T; i++) {
            Trie trie = new Trie();

            for (int j = 0; j < N[i]; j++) {
                trie.insert(numbers[i][j]);
            }
            
            boolean isValidCase = true;
            for (int j = 0; j < N[i]; j++) {
                List<String> withPrefixList = trie.findAllWithPrefix(numbers[i][j]);
                if (withPrefixList.size() > 1) {
                    isValidCase = false;
                    break;
                }
            }

            if (isValidCase) {
                System.out.println("YES");
            }else {
                System.out.println("NO");
            }
        }

    }

    private static class TrieNode {
        private final int ALPHABET_SIZE = 10;
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];
        boolean isEndOfWord;
        String word; // 노드에서 완성되는 단어를 저장하기 위한 변수

        public TrieNode() {
        }
    }

    public static class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String key) {
            TrieNode node = root;
            for (int level = 0; level < key.length(); level++) {
                int index = key.charAt(level) - '0';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.isEndOfWord = true;
            node.word = key; // 삽입하는 단어를 해당 노드에 저장
        }

        // 접두사로 시작하는 모든 문자열을 찾는 메서드
        public List<String> findAllWithPrefix(String prefix) {
            TrieNode node = root;
            for (int level = 0; level < prefix.length(); level++) {
                int index = prefix.charAt(level) - '0';
                if (node.children[index] == null) return new ArrayList<>();
                node = node.children[index];
            }

            List<String> results = new ArrayList<>();
            findWordsFrom(node, results);
            return results;
        }

        // 주어진 노드로부터 시작하여 모든 단어를 찾는 보조 메서드
        private void findWordsFrom(TrieNode node, List<String> results) {
            if (node == null) {
                return;
            }

            if (node.isEndOfWord) {
                results.add(node.word);
            }

            for (TrieNode child : node.children) {
                findWordsFrom(child, results);
            }
        }
    }
}
