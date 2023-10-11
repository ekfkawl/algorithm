
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static int R;
    static int C;
    static int K;

    static int[][] graph;

    static ArrayList<Operator> operators = new ArrayList<>();

    static int res = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        String[] str = br.readLine().split(" ");
        R = Integer.parseInt(str[0]);
        C = Integer.parseInt(str[1]);
        K = Integer.parseInt(str[2]);

        graph = new int[R][C];

        for (int i = 0; i < R; i++) {
            graph[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < K; i++) {
            str = br.readLine().split(" ");
            int r = Integer.parseInt(str[0]);
            int c = Integer.parseInt(str[1]);
            int s = Integer.parseInt(str[2]);
            operators.add(new Operator(r, c, s));
        }

        dfs(0);
        System.out.println(res);
    }

    // 순열
    public static void dfs(int depth) {
        if (depth == operators.size()) {
            res = Math.min(res, getMinSum());
            return;
        }

        for (Operator operator : operators) {
            if (operator.visited) {
                continue;
            }

            operator.visited = true;
            int[][] prev = graph;
            graph = rotationGraph(operator);
            dfs(depth + 1);
            graph = prev;
            operator.visited = false;
        }
    }

    public static int getMinSum() {
        int min = Integer.MAX_VALUE;
        for (int[] v : graph) {
            min = Math.min(min, Arrays.stream(v).sum());
        }
        return min;
    }

    public static int[][] rotationGraph(Operator o) {
        int[][] res = deepCopyGraph();

        Point leftStart = new Point(o.r - o.s - 1, o.c - o.s - 1);
        Point rightEnd = new Point(o.r + o.s - 1, o.c + o.s - 1);

        int width = rightEnd.x - leftStart.x + 1;
        int height = rightEnd.y - leftStart.y + 1;
        int min = Math.min(width, height);

        while (min > 0) {
            int row = leftStart.y;
            int col = leftStart.x;

            for (col = col + 1; col <= rightEnd.x; col++) {
                res[row][col] = graph[row][col - 1];
            }

            col--;
            for (row = row + 1; row <= rightEnd.y; row++) {
                res[row][col] = graph[row - 1][col];
            }

            row--;
            for (col = col - 1; col >= leftStart.x; col--) {
                res[row][col] = graph[row][col + 1];
            }

            col++;
            for (row = row - 1; row >= leftStart.y; row--) {
                res[row][col] = graph[row + 1][col];
            }

            leftStart.x++;
            leftStart.y++;
            rightEnd.x--;
            rightEnd.y--;
            min--;
        }

        return res;
    }

    private static int[][] deepCopyGraph() {
        int[][] res = graph.clone();
        for (int i = 0; i < R; i++) {
            res[i] = graph[i].clone();
        }
        return res;
    }

    public static class Operator {
        int r;
        int c;
        int s;
        boolean visited;

        public Operator(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.visited = false;
        }
    }

    public static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
