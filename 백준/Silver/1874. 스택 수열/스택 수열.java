
import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();

        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ) {
            int N = Integer.parseInt(br.readLine()); // 명령어 개수만큼 반복하기 위함

            int position = 0;
            int halt = 0;

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
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
