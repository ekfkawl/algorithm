
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    static int N;
    static Deque<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            command(br.readLine());
        }
    }

    static void command(String cmd) {
        if (cmd.startsWith("push ")) {
            String[] s = cmd.split(" ");
            queue.add(Integer.parseInt(s[1]));
        }

        else if (cmd.equals("pop")) {
            if (queue.isEmpty()) {
                System.out.println(-1);
                return;
            }
            System.out.println(queue.pollFirst());
        }

        else if (cmd.equals("size")) {
            System.out.println(queue.size());
        }

        else if (cmd.equals("empty")) {
            System.out.println(queue.isEmpty() ? 1 : 0);
        }

        else if (cmd.equals("front")) {
            if (queue.isEmpty()) {
                System.out.println(-1);
                return;
            }
            System.out.println(queue.peek());
        }

        else if (cmd.equals("back")) {
            if (queue.isEmpty()) {
                System.out.println(-1);
                return;
            }
            System.out.println(queue.peekLast());
        }
    }
}
