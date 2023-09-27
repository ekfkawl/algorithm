
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;

    static long[] dp = new long[100_000+1];

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        /**
         * 거스름돈 N원에 해당하는 최소 동전 개수를 dp로 구한다.
         *
         */
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 0;
        dp[4] = 2;
        dp[5] = 1;
        dp[6] = 3;
        dp[7] = 2;
        dp[8] = 4;
        dp[9] = 3;

        for (int i = 10; i <= N; i++) {
            if (i % 5 == 0) {
                dp[i] = i/5;
            }else {
                dp[i] = Integer.MAX_VALUE;
                for (int j = 1; j*5 < i; j++) {
                    if (dp[i - j*5] != 0) {
                        dp[i] = Math.min(dp[i], dp[i - j*5] + j);
                    }
                }

                if (dp[i] == Integer.MAX_VALUE) {
                    dp[i] = 0;
                }
            }
        }

        if (dp[N] == 0) {
            System.out.println(-1);
        }else {
            System.out.println(dp[N]);
        }
    }
}
