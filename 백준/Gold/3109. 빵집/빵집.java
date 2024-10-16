
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int R;
    static int C;
    static char[][] graph;

    static int[] dy = {-1, 0, 1};
    static int[] dx = {1, 1, 1};

    static boolean[][] visited;

    static class Position {
        int y;
        int x;

        public Position(int y, int x) {
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
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            graph[i] = br.readLine().toCharArray();
        }

        int count = 0;
        for (int i = 0; i < R; i++) {
            if (graph[i][0] == '.') {
                if (dfs(new Position(i, 0))) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    static boolean dfs(Position start) {
        visited[start.y][start.x] = true;

        if (start.x == C - 1) {
            return true;
        }else {
            for (int i = 0; i < 3; i++) {
                int ny = start.y + dy[i];
                int nx = start.x + dx[i];

                if (ny >= 0 && ny < R && nx >= 0 && nx < C && !visited[ny][nx] && graph[ny][nx] == '.') {
                    if (dfs(new Position(ny, nx))) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}