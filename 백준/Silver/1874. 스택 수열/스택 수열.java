

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int position = 0;

        for (int i = 1; i <= N; i++) {
            int v = Integer.parseInt(br.readLine());
            if (position < v) {
                for (int j = position; j < v; j++) {
                    sb.append("+\n");
                    position++;
                    stack.push(position);
                }
            }
            if (stack.peek() != v) {
                sb.setLength(0);
                sb.append("NO\n");
                break;
            }
            sb.append("-\n");
            stack.pop();
        }
        
        System.out.println(sb.toString());
    }
}
