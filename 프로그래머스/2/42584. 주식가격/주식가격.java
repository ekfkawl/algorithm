import java.util.PriorityQueue;
import java.util.Queue;

class Solution {

    public static int[] solution(int[] prices) {

        int[] res = new int[prices.length];

        int dest = 0;
        while (dest < prices.length) {

            boolean f = false;
            for (int i = dest + 1; i < prices.length; i++) {
                if (prices[dest] > prices[i]) {
                    res[dest] = i - dest;
                    f = true;
                    break;
                }
            }

            if (!f) {
                res[dest] = prices.length - (dest + 1);
            }

            dest++;
        }

        return res;
    }
}