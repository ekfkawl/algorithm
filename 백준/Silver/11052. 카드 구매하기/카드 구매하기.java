
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;

    static int[] P;

    static long[] dp = new long[1000+1];

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        P = new int[N+1];

        int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 1; i <= N; i++) {
            P[i] = array[i-1];
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                // dp[i]: 현재 카드 i개 까지의 최대 금액
                // dp[i-j]: i-j개 까지의 최대 금액
                dp[i] = Math.max(dp[i], dp[i-j] + P[j]);
            }
        }

        System.out.println(dp[N]);
    }
 }
