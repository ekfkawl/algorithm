
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int T;
    static int N;
    static int[][] score = new int[2][];

    static int[][] dp;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            score[0] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            score[1] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            dp = new int[2][N];

            dp[0][0] = score[0][0];
            dp[1][0] = score[1][0];

            for (int i = 1; i < N; i++) {
                int noSelect = Math.max(dp[0][i-1], dp[1][i-1]);

                for (int j = 0; j <= 1; j++) {
                    int select = dp[1-j][i-1] + score[j][i];

                    dp[j][i] = Math.max(noSelect, select);
                }
            }

            System.out.println(Math.max(dp[0][N-1], dp[1][N-1]));
        }


    }
}
