

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;

    static char[] chars;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        chars = new char[N];

        for (int i = 0; i < N; i++) {
            chars[i] = br.readLine().charAt(0);
        }

        StringBuilder sb = new StringBuilder();

        int l = 0;
        int r = N - 1;

        int TLength = 0;
        while (l <= r) {
            int i = 0;

            boolean isLeft = false;
            while (l + i <= r) {
                char lt = chars[l + i];
                char rt = chars[r - i];

                if (lt < rt) {
                    isLeft = true;
                    break;
                }

                if (lt > rt) {
                    break;
                }

                i++;
            }

            if (isLeft) {
                sb.append(chars[l++]);
            }else {
                sb.append(chars[r--]);
            }
            TLength++;

            if (N >= 80 && TLength % 80 == 0) {
                sb.append("\n");
            }
        }

        System.out.println(sb);

    }
}
