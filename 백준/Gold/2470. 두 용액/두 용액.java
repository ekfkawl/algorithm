import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static int[] nums;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        int l = 0;
        int r = N-1;
        int min = Integer.MAX_VALUE;
        int lv = 0;
        int rv = 0;

        while (l < r) {
            int sum = nums[l] + nums[r];
            if (min > Math.abs(sum)) {
                min = Math.abs(sum);

                lv = nums[l];
                rv = nums[r];
            }

            if (sum > 0) {
                r--;
            } else {
                l++;
            }
        }

        System.out.print(lv + " " + rv);
    }
}
