
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static int n;
    static int m;

    static boolean[] visited = new boolean[9];

    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");

        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);

        recur(1, 0);
    }

    public static void recur(int idx, int cur) {
        if (cur >= m) {
            for (Integer v : stack) {
                System.out.print(v + " ");
            }
            System.out.println();
            return;
        }

        for (int i = idx ; i <= n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                stack.push(i);

                recur(i + 1, cur + 1);

                stack.pop();
                visited[i] = false;

            }
        }

    }
}
