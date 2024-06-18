import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N; // 벨트에 놓인 접시 수
    static int D; // 가짓 수
    static int K; // 연속해서 먹는 접시 수
    static int C; // 쿠폰

    static int[] items;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        N = arr[0];
        D = arr[1];
        K = arr[2];
        C = arr[3];

        items = new int[N + K];

        for (int i = 0; i < N; i++) {
            items[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < K; i++) {
            items[N + i] = items[i];
        }

        Map<Integer, Integer> map = new HashMap<>();

        int l = 0;
        int r = 0;
        int max = Integer.MIN_VALUE;

        while (r < items.length) {
            if (r - l < K) {
                map.put(items[r], map.getOrDefault(items[r], 0) + 1);

                r++;
            }else {
                map.computeIfPresent(items[l], (k, v) -> {
                    if (v == 1) {
                        return null;
                    } else {
                        return v - 1;
                    }
                });

                l++;
            }

            max = Math.max(max, map.size());
            if (map.getOrDefault(C, 0) == 0) {
                max = Math.max(max, map.size() + 1);
            }
        }

        System.out.println(max);
    }
}
