import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    /**
     * 6
     *
     * 10
     * 20
     * 15
     * 25
     * 10
     * 20
     */

    static int n;
    static int[] dp = new int[300+1];
    static int[] score;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        score = new int[n];

        for (int i = 0; i < n; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = score[0];
        if (n > 1) {
            dp[1] = score[0] + score[1];
        }
        if (n > 2) {
            dp[2] = Math.max(score[0] + score[2], score[1] + score[2]);
        }

        for (int i = 3; i < n; i++) {
            dp[i] = Math.max(dp[i-2] + score[i],  dp[i-3] + score[i-1] + score[i]);
        }

        System.out.println(dp[n-1]);

    }
}