
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            Stack<Character> stack = new Stack<>();

            boolean no = false;
            char[] arr = br.readLine().toCharArray();
            for (char c : arr) {
                if (c == '(') {
                    stack.push(c);
                }else {
                    if (stack.isEmpty()) {
                        no = true;
                        break;
                    }
                    stack.pop();
                }
            }

            if (stack.isEmpty() && !no) {
                System.out.println("YES");
            }else {
                System.out.println("NO");
            }
        }
    }
}
