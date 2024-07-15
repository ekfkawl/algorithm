
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    static int[] limit = new int[3];
    static boolean[][][] visited = new boolean[201][201][201];
    static Queue<Integer> queue = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        limit = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dfs(0, 0, limit[2]);
        while (!queue.isEmpty()) {
            System.out.print(queue.poll() + " ");
        }
    }

    static void dfs(int a, int b, int c) {
        if (visited[a][b][c]) {
            return;
        }
        visited[a][b][c] = true;

        if (a == 0) {
            queue.add(c);
        }

        int[] cur = {a, b, c};

        for (int from = 0; from < 3; from++) {
            for (int to = 0; to < 3; to++) {
                if (from == to) {
                    continue;
                }

                if (cur[from] + cur[to] <= limit[to]) {
                    cur[to] += cur[from];
                    cur[from] = 0;
                }else {
                    cur[from] -= (limit[to] - cur[to]);
                    cur[to] = limit[to];
                }

                dfs(cur[0], cur[1], cur[2]);

                cur[0] = a;
                cur[1] = b;
                cur[2] = c;
            }
        }

    }
}
