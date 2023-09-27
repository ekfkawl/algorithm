
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static int N;

    static int[][] graph;

    static ArrayList<Point> wBishopPoint = new ArrayList<>();
    static ArrayList<Point> bBishopPoint = new ArrayList<>();

    static int res = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];

        for (int i = 0; i < N; i++) {
            graph[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < N; j++) {
                if (graph[i][j] == 1) {
                    if ((i + j) % 2 == 0) {
                        wBishopPoint.add(new Point(i, j));
                    }else {
                        bBishopPoint.add(new Point(i, j));
                    }
                }
            }
        }

        int a = 0;
        int b = 0;

        dfs(wBishopPoint, 0, 0);
        a = res;
        res = 0;

        dfs(bBishopPoint, 0, 0);
        b = res;

        System.out.println(a + b);
    }

    public static void dfs(ArrayList<Point> points, int depth, int count) {
        // 오른쪽 아래 끝까지 스캔하면 종료
        if (depth == points.size()) {
            res = Math.max(res, count);
            return;
        }

        Point cur = points.get(depth);
        if (cur.isSafePosition()) {
            // 비숍을 놓는 경우
            count++;
            graph[cur.y][cur.x] = 2;
            dfs(points, depth + 1, count);
            count--;
            graph[cur.y][cur.x] = 1;
        }

        dfs(points, depth + 1, count);
    }

    public static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

//        public boolean isSafePosition() {
//            int[][] directions = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
//            for (int[] dir : directions) {
//                int newY = this.y;
//                int newX = this.x;
//                while (newY >= 0 && newY < N && newX >= 0 && newX < N) {
//                    if (graph[newY][newX] == 2) return false;
//                    newY += dir[0];
//                    newX += dir[1];
//                }
//            }
//            return true;
//        }


        public boolean isSafePosition() {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (graph[i][j] != 2) {
                        continue;
                    }

                    if (Math.abs(this.y - i) == Math.abs(this.x - j)) {
                        return false;
                    }
                }
            }
            return true;
        }

    }
}
