
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, M;

    static int[] parent, rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        rank = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < N; i++) {
            int[] v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j < v.length; j++) {
                if (v[j] == 1) {
                    union(i + 1, j + 1);
                }
            }
        }

        int[] v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int set = find(v[0]);
        
        boolean isTravel = true;
        for (int i = 1; i < v.length; i++) {
            if (find(v[i]) != set) {
                isTravel = false;
                break;
            }
        }

        System.out.println(isTravel ? "YES" : "NO");
     
    }

    static int find (int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }
}
