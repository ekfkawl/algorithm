
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int S;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        S = Integer.parseInt(s[1]);

        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int l = 0;
        int r = 0;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        while (r < N) {
            sum += nums[r];

            while (sum >= S) {
                min = Math.min(min, r - l + 1);
                sum -= nums[l++];
            }
            
            r++;
        }

        System.out.println(min == Integer.MAX_VALUE ? 0 : min);
    }



}
