
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N, M;
    static int[][] map;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);

        map = new int[N][];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int cnt = 0;
        int size = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    size = Math.max(size, dfs(new Point(i, j)));
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
        System.out.println(size);
    }

    static int dfs(Point point) {
        map[point.y][point.x] = 2; // 그림 개수를 카운트를 위한 플래그
        int cnt = 1;

        for (int i = 0; i < 4; i++) {
            Point next = new Point(point.y + dy[i], point.x + dx[i]);
            if (isSafe(next.y, next.x) && map[next.y][next.x] == 1) {
                cnt += dfs(next);
            }
        }

        return cnt;
    }

    static boolean isSafe(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }
}
