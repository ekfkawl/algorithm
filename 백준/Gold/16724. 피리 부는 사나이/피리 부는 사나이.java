

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int R, C;

    static char[][] graph;
    static byte[][] visited;

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

        String[] s = br.readLine().split(" ");
        R = Integer.parseInt(s[0]);
        C = Integer.parseInt(s[1]);

        graph = new char[R][C];

        for (int i = 0; i < R; i++) {
            graph[i] = br.readLine().toCharArray();
        }

        visited = new byte[R][C];

        int count = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (visited[i][j] == 0 && dfs(new Point(i, j))) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    static boolean dfs(Point p) {
        if (visited[p.y][p.x] == 127) {
            return false;
        }

        if (++visited[p.y][p.x] == 2) {
            return true;
        }

        int ny = p.y;
        int nx = p.x;

        switch (graph[p.y][p.x]) {
            case 'U': ny--; break;
            case 'D': ny++; break;
            case 'L': nx--; break;
            case 'R': nx++; break;
        }

        boolean res = dfs(new Point(ny, nx));
        visited[p.y][p.x] = 127;

        return res;
    }
}
