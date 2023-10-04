
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;

    static int[] nums;

    static int[] dp = new int[1000+1];

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dp[0] = nums[0];
        int max = dp[0];
        
        for (int i = 1; i < N; i++) {
            dp[i] = nums[i];

            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + nums[i]);
                }
            }

            max = Math.max(max, dp[i]);

        }

        System.out.println(max);

    }
}
