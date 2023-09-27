
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int T;
    static int[] N;

    static long[] dp;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        N = new int[T+1];

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < T; i++) {
            N[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, N[i]);
        }
        dp = new long[max+1];

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        dp[4] = 7;

        for (int i = 5; i <= max; i++) {
            dp[i] = (dp[i-1] + dp[i-2] + dp[i-3]) % 1_000_000_009;
        }

        for (int i = 0; i < T; i++) {
            System.out.println(dp[N[i]] % 1_000_000_009);
        }
    }
}
