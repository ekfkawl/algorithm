import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            queue.add(Integer.parseInt(br.readLine()));
        }

        int res = 0;
        while (queue.size() >= 2) {
            int a = queue.poll();
            int b = queue.poll();

            res += a + b;
            queue.add(a + b);
        }

        System.out.println(res);
    }
}
