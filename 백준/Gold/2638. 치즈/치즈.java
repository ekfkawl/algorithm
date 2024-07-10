import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N; // 세로
    static int M; // 가로

    static int[][] graph;

    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = v[0];
        M = v[1];


        graph = new int[N][M];
        for (int i = 0; i < N; i++) {
            v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.arraycopy(v, 0, graph[i], 0, M);
        }

        int res = 0;

        while (true) {
            visited = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                if (graph[i][0] == 0 && !visited[i][0]) {
                    dfs(i, 0);
                }
                if (graph[i][M - 1] == 0 && !visited[i][M - 1]) {
                    dfs(i, M - 1);
                }
            }
            for (int j = 0; j < M; j++) {
                if (graph[0][j] == 0 && !visited[0][j]) {
                    dfs(0, j);
                }
                if (graph[N - 1][j] == 0 && !visited[N - 1][j]) {
                    dfs(N - 1, j);
                }
            }

            if (!melt()) {
                break;
            }
            res++;
        }


        System.out.println(res);
    }

    static void dfs(int y, int x) {
        visited[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (!(ny >= 0 && ny < N && nx >= 0 && nx < M)) {
                continue;
            }

            if (visited[ny][nx]) {
                continue;
            }

            if (graph[ny][nx] == 1) {
                continue;
            }

            dfs(ny, nx);
        }
    }

    static boolean melt() {
        boolean isMelted = false;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (graph[i][j] == 1) {

                    int airCount = 0;
                    for (int k = 0; k < 4; k++) {
                        int ny = i + dy[k];
                        int nx = j + dx[k];

                        if (!(ny >= 0 && ny < N && nx >= 0 && nx < M)) {
                            continue;
                        }

                        if (visited[ny][nx]) {
                            if (++airCount >= 2) {
                                graph[i][j] = 0;
                                isMelted = true;
                                break;
                            }
                        }
                    }
                }
            }
        }

        return isMelted;
    }

}
