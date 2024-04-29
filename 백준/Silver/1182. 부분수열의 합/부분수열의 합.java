import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int N; // 정수의 개수
    static int S; // 더했을 때 목표 값

    static List<Number> nums = new ArrayList<>();

    static int count = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        S = Integer.parseInt(s[1]);

        s = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            nums.add(new Number(Integer.parseInt(s[i]), false));
        }

        bt(0, 0);
        System.out.println(count);
    }

    public static class Number {
        int n;
        boolean visited;

        public Number(int n, boolean visited) {
            this.n = n;
            this.visited = visited;
        }
    }

    public static void bt(int sum, int start) {
        if (start > 0 && sum == S) {
            count++;
        }

        for (int i = start; i < N; i++) {
            Number next = nums.get(i);

            if (!next.visited) {
                next.visited = true;
                bt(sum + next.n, i + 1);
                next.visited = false;
            }
        }

    }
}
