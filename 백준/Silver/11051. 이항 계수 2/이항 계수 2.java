
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int K;

    static int[][] dp = new int[1000+1][1000+1];
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        K = Integer.parseInt(s[1]);

        System.out.println(combination(N, K) % 10_007);
    }

    public static int combination(int n, int k) {
        if (k == 0 || n == k) {
            return dp[n][k] = 1;
        }

        if (dp[n][k] > 0) {
            return dp[n][k];
        }

        // 원소를 선택한 경우 + 선택하지 않은 경우
        return dp[n][k] = (combination(n-1, k-1) + combination(n-1, k)) % 10007;
    }

}
