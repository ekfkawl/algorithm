

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] maze = new int[N + 1][M + 1];
        int[][] dp = new int[N + 1][M + 1];

        // 미로 정보 입력
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp[i][j] - (1,1)에서 (i,j)까지 이동하며 얻을 수 있는 최대 사탕수
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = maze[i][j] + Math.max(dp[i - 1][j], Math.max(dp[i][j - 1], dp[i - 1][j - 1]));
            }
        }

        System.out.println(dp[N][M]);
    }
}
