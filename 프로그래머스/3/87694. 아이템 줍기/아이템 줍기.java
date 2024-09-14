
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static byte[][] graph = new byte[51 * 2][51 * 2];
    static int[][] dist = new int[51 * 2][51 * 2];

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};


    static class Rect {
        int bx;
        int by;
        int tx;
        int ty;

        public Rect(int[] r) {
            this.bx = r[0] * 2;
            this.by = r[1] * 2;
            this.tx = r[2] * 2;
            this.ty = r[3] * 2;
        }
    }

    static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        Rect[] rects = Arrays.stream(rectangle)
                .map(Rect::new)
                .toArray(Rect[]::new);

        // draw
        for (Rect rect : rects) {
            for (int i = rect.by; i <= rect.ty; i++) {
                for (int j = rect.bx; j <= rect.tx; j++) {
                    graph[i][j] = 1;
                }
            }
            System.out.println();
        }

        // optimize
        for (Rect rect : rects) {
            for (int i = rect.by + 1; i <= rect.ty - 1; i++) {
                for (int j = rect.bx + 1; j <= rect.tx - 1; j++) {
                    graph[i][j] = 0;
                }
            }
        }

        for (int[] arr : dist) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        return bfs(new Point(characterY * 2, characterX * 2), new Point(itemY * 2, itemX * 2));
    }

    public static int bfs(Point start, Point dest) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        dist[start.y][start.x] = 1;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            if (cur.y == dest.y && cur.x == dest.x) {
                return dist[dest.y][dest.x] / 2;
            }

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                if (!(nx >= 0 && nx < graph[0].length && ny >= 0 && ny < graph.length)) {
                    continue;
                }

                if (graph[ny][nx] == 0) {
                    continue;
                }

                if (dist[ny][nx] > dist[cur.y][cur.x] + 1) {
                    dist[ny][nx] = dist[cur.y][cur.x] + 1;
                    queue.add(new Point(ny, nx));
                }
            }
        }

        return -1;
    }

}
