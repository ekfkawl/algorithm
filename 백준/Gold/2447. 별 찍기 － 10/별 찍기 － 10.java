
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                draw(i, j, N);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void draw(int i, int j, int n) {
        if ((i / n) % 3 == 1 && (j / n) % 3 == 1) {
            sb.append(" ");
        }else if (n == 1) {
            sb.append("*");
        }else {
            draw(i, j, n / 3);
        }
    }
}
