
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int K; // 최대 무게

    static int[] W = new int[100+1];
    static int[] V = new int[100+1];

    // i번째 물품까지, j까지의 무게 최대 가치
    static long[][] dp;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        K = Integer.parseInt(s[1]);
        dp = new long[N+1][K+1];

        for (int i = 1; i <= N; i++) {
            s = br.readLine().split(" ");
            W[i] = Integer.parseInt(s[0]);
            V[i] = Integer.parseInt(s[1]);
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                // 현재 무게보다 물품의 무게가 크면 물품을 담을 수 없음
                if (j < W[i]) {
                    dp[i][j] = dp[i-1][j];
                }else {
                    // 현재 물품을 선택 안하는 경우, 현재 물품을 선택하는 경우
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-W[i]] + V[i]);
                }
            }
        }

        System.out.println(dp[N][K]);
    }

}
