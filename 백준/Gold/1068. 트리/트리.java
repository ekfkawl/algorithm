
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static int N;
    static int[] nodes;

    static int T;

    static List<List<Integer>> tree = new ArrayList<>();

    static int count;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        nodes = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<>());
        }

        T = Integer.parseInt(br.readLine());

        int root = 0;
        for (int i = 0; i < N; i++) {
            if (nodes[i] == -1) {
                root = i;
                continue;
            }

            if (i == T) {
                continue;
            }

            if (nodes[i] == T) {
                continue;
            }

            tree.get(nodes[i]).add(i);

        }

        dfs(root);
        
        System.out.println(count);
    }

    static void dfs(int node) {
        if (node == T) {
            return;
        }

        List<Integer> child = tree.get(node);

        if (child.size() == 0) {
//            System.out.println(node);
            count++;
        }else {
            for (int c : child) {
                dfs(c);
            }
        }
    }
}
