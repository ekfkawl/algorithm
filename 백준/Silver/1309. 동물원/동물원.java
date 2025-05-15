import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;

    static int[][] dp;

//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        N = Integer.parseInt(br.readLine());
//
//        dp = new int[N][3];
//
//        dp[0][0] = 1;
//        dp[0][1] = 1;
//        dp[0][2] = 1;
//
//        for (int i = 1; i < N; i++) {
//            dp[i][0] = dp[i-1][0] + dp[i-1][1] + dp[i-1][2];
//            dp[i][1] = dp[i-1][0] + dp[i-1][2];
//            dp[i][2] = dp[i-1][0] + dp[i-1][1];
//        }
//
//        System.out.println(dp[N-1][0] + dp[N-1][1] + dp[N-1][2]);
//    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());


        int c0 = 1;
        int c1 = 1;
        int c2 = 1;

        for (int i = 1; i < N; i++) {
            int _c0 = (c0 + c1 + c2) % 9901;
            int _c1 = (c0 + c2) % 9901;
            int _c2 = (c0 + c1) % 9901;

            c0 = _c0;
            c1 = _c1;
            c2 = _c2;
        }

        System.out.println((c0 + c1 + c2) % 9901);
    }
}
