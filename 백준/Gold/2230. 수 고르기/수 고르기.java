import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

public class Main {
    static int N;
    static int M;
    static int[] nums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);

        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(nums);

        int l = 0;
        int r = 1;
        int min = Integer.MAX_VALUE;

        while (r < N) {
            int diff = nums[r] - nums[l];
            if (diff >= M) {
                min = Math.min(min, diff);
                l++;
            }else {
                r++;
            }
            
            if (l == r) {
                r++;
            }
        }

        System.out.println(min);
    }
}
