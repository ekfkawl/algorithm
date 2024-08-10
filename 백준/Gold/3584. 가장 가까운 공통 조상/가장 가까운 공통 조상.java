
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int T;

    static List<List<Integer>> graph;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

            graph = new ArrayList<>();
            for (int j = 0; j <= N; j++) {
                graph.add(new ArrayList<>());
            }

            for (int j = 0; j < N - 1; j++) {
                int[] v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int parent = v[0];
                int child = v[1];
                graph.get(child).add(parent);
            }

            int[] v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int a = v[0];
            int b = v[1];

            int dfs1 = dfs(a, b);
            int dfs2 = dfs(b, a);

            if (dfs1 != 0) {
                System.out.println(dfs1);
            }else {
                System.out.println(dfs2);
            }
        }

    }

    static int dfs(int src, int dest) {
        int res = 0;

        if (src == dest) {
            return dest;
        }

        for (int srcParent : graph.get(src)) {
            res = dfs(srcParent, dest);
            if (res == 0) {
                for (int destParent : graph.get(dest)) {
                   res = dfs(srcParent, destParent);
                }
            }
        }

        return res;
    }
}
