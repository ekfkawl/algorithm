
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {

    static int N;

    static byte[] dp = new byte[1000+1];

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(System.in)));

        N = Integer.parseInt(br.readLine());

        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 0;
        dp[4] = 1;
        dp[5] = 0;

        for (int i = 6; i <= N; i++) {
            dp[i] = dp[i-2];
        }

        System.out.println(dp[N] == 1 ? "SK" : "CY");
    }
}
