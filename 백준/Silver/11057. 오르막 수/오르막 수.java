import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;

    static long[][] dp = new long[1000+1][10];

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            // 9로 시작하는 수는 이전 자리수의 9로 시작하는 값
            dp[i][9] = dp[i-1][9];

            // j로 시작하는 수
            for (int j = 8; j >= 0; j--) {
                dp[i][j] = dp[i-1][j] + dp[i][j+1] % 10_007;
            }

//            for (int j = 1; j <= 9; j++) {
//                if (j == 9) {
//                    dp[i][j] = dp[i-1][j] % 10_007;
//                }else {
//                    for (int k = j; k <= 9; k++) {
//                        dp[i][j] += dp[i-1][k] % 10_007;
//                    }
//                }
            
//                sum += dp[i][j];
//            }
        }

        long sum = 0;
        for (int i = 0; i <= 9; i++) {
            sum += dp[N][i];
        }
        System.out.println(sum % 10_007);
    }
}
