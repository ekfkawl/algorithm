
import java.util.PriorityQueue;
import java.util.*;

public class Solution {


    public static long solution(int n, int[] times) {
       // Arrays.sort(times);
        long left = 0;
        long right = (long) times[times.length - 1] * n;
        long answer = right;

        while (left <= right) {
            long mid = (left + right) / 2;
            long people = 0;

            for (int time : times) {
                people += mid / time;
            }

            if (people < n) {
                left = mid + 1;
            } else {
                answer = mid;
                right = mid - 1;
            }
        }

        return answer;
    }

}
