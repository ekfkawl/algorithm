
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;

    static int[] dp = new int[100_000+1];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());


        for (int i = 1; i <= N; i++) {
            // 최대 i
            dp[i] = i;

            for (int j = 1; j <= Math.sqrt(i); j++) {
                dp[i] = Math.min(dp[i], dp[(int)(i-Math.pow(j, 2))] + 1);
            }
        }

        System.out.println(dp[N]);

    }
}
