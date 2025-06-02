import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    static int N;

    static class Time implements Comparable<Time> {
        int start;
        int end;

        public Time(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Time o) {
            if (this.end != o.end) {
                return Integer.compare(this.end, o.end);
            }
 
            return Integer.compare(this.start, o.start);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        Queue<Time> queue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split(" ");

            int start = Integer.parseInt(s[0]);
            int end = Integer.parseInt(s[1]);
            Time time = new Time(start, end);
            queue.add(time);
        }

        Time current = queue.poll();
        int max = 1;

        while (!queue.isEmpty()) {
            Time time = queue.poll();

            if (time.start < current.end) {
                continue;
            }

            current = time;
            max++;
        }

        System.out.println(max);
    }
}
