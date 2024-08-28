
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int c = 1; c < Integer.MAX_VALUE; c++) {
            int[] v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (v[0] + v[1] == 0) {
                break;
            }

            int N = v[0];
            int M = v[1];

            int[] parent = new int[N + 1];
            int[] rank = new int[N + 1];
            boolean[] isGraph = new boolean[N + 1];

            for (int i = 0; i <= N; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < M; i++) {
                v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                if (!union(parent, rank, v[0], v[1])) {
                    isGraph[find(parent, v[0])] = true;
                }
            }

            Set<Integer> set = new HashSet<>();
            for (int i = 1; i <= N; i++) {
                if (!isGraph[find(parent, i)]) {
                    set.add(find(parent, i));
                }
            }


            StringBuilder sb = new StringBuilder();
            sb.append(String.format("Case %d: ", c));

            int size = set.size();
            if (size == 0) {
                sb.append("No trees.");
            }else if (size == 1) {
                sb.append("There is one tree.");
            }else {
                sb.append(String.format("A forest of %d trees.", size));
            }

            System.out.println(sb.toString());

        }
    }

    static int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    static boolean union(int[] parent, int[] rank, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);

        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            }else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            }else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }

            return true;
        }

        return false;
    }
}
