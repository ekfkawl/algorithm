import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    static int N, M;

    static int[] K;

    static int[] parent, rank;

    static Queue<Party> queue = new PriorityQueue<>();

    static class Party implements Comparable<Party> {
        int count;
        int[] node;

        public Party(int count, int[] node) {
            this.count = count;
            this.node = node;
        }

        @Override
        public int compareTo(Party o) {
            return Integer.compare(o.count, this.count);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        if (arr[0] > 0) {
            K = new int[arr[0]];
            System.arraycopy(arr, 1, K,0, arr.length - 1);
        }else {
            System.out.println(M);
            System.exit(0);
        }

        parent = new int[N + 1];
        rank = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 1; i < K.length; i++) {
            union(K[0], K[i]);
        }

        for (int i = 0; i < M; i++) {
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int count = arr[0];
            int[] node = new int[count];
            System.arraycopy(arr, 1, node,0, count);

            queue.add(new Party(count, node));

            for (int j = 1; j < count; j++) {
                union(node[0], node[j]);
            }
        }


        int res = 0;
        while (!queue.isEmpty()) {
            Party poll = queue.poll();

            boolean isKnow = false;

            int[] node = poll.node;
            for (int a : node) {
                if (find(a) == find(K[0])) {
                    isKnow = true;
                    break;
                }
            }

            if (!isKnow) {
                res++;
            }
        }

        System.out.println(res);
    }

    static int find(int x) {
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
            }else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            }else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }
}
