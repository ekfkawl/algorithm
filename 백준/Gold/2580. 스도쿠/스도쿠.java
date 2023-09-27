import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[][] graph = new int[9][9];

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            graph[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        dfs(0, 0);

    }

    public static void dfs(int y, int x) {
        // 해당 y축의 가로칸을 다 검사했으면 다음 y축부터 다시 검사
        if (x >= 9) {
            y++;
            x = 0;
        }

        // 오른쪽 끝 행까지 검사했으면 출력
        if (y >= 9) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(graph[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb.toString());
            System.exit(0);
            return;
        }

        // 빈칸이면 다음칸 검사
        if (graph[y][x] != 0) {
            dfs(y, x + 1);
            return;
        }

        // 빈칸이면 1~9까지 놓아본다
        for (int i = 1; i <= 9; i++) {
            if (!check(y, x, i)) {
                continue;
            }

            graph[y][x] = i;
            dfs(y, x + 1);
            graph[y][x] = 0;
        }
    }

    public static boolean check(int y, int x, int v) {
        // 각각의 가로줄과 세로줄에는 1부터 9까지의 숫자가 한 번씩만 나타나야 한다.
        for (int i = 0; i < 9; i++) {
            if (graph[y][i] == v) {
                return false;
            }

            if (graph[i][x] == v) {
                return false;
            }
        }

        // 굵은 선으로 구분되어 있는 3x3 정사각형 안에도 1부터 9까지의 숫자가 한 번씩만 나타나야 한다.
        int sy = 0;
        int sx = 0;

        // y
        if (y <= 2) {
            sy = 0;
        }
        else if (y <= 5) {
            sy = 3;
        }
        else if (y <= 8) {
            sy = 6;
        }

        // x
        if (x <= 2) {
            sx = 0;
        }
        else if (x <= 5) {
            sx = 3;
        }
        else if (x <= 8) {
            sx = 6;
        }


        for (int i = sy; i <= sy+2; i++) {
            for (int j = sx; j <= sx+2; j++) {
                if (graph[i][j] == v) {
                    return false;
                }
            }
        }

        return true;
    }
}
