
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    static int N;
    static int[] nums;
    static int[] dp = new int[1000+1];
    static int[] parent = new int[1000+1];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.fill(parent, -1);
        int max = 0;
        int maxIndex = 0;

        for (int i = 0; i < N; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                }
            }

            if (max < dp[i]) {
                max = dp[i];
                maxIndex = i;
            }
        }

        System.out.println(max);

        Stack<Integer> stack = new Stack<>();
        while (maxIndex != -1) {
            stack.add(nums[maxIndex]);
            maxIndex = parent[maxIndex];
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}
