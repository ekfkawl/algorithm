
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static int[][] graph;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int[][] dp;

    static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        graph = new int[N][];
        for (int i = 0; i < N; i++) {
            graph[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        dp = new int[N][N];

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int dfs = dfs(new Point(i, j));
                max = Math.max(max, dfs);
            }
        }

        System.out.println(max);
    }

    static int dfs(Point cur) {
        if (dp[cur.y][cur.x] > 0) {
            return dp[cur.y][cur.x];
        }

        dp[cur.y][cur.x] = 1;

        for (int i = 0; i < 4; i++) {
            int ny = cur.y + dy[i];
            int nx = cur.x + dx[i];

            if (!(ny >= 0 && ny < N && nx >= 0 && nx < N)) {
                continue;
            }

            if (graph[ny][nx] <= 0) {
                continue;
            }

            if (graph[cur.y][cur.x] < graph[ny][nx]) {
                int next = dfs(new Point(ny, nx)) + 1;
                dp[cur.y][cur.x] = Math.max(dp[cur.y][cur.x], next);
            }

        }

        return dp[cur.y][cur.x];
    }
}
