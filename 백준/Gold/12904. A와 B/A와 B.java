

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        StringBuilder T = new StringBuilder(br.readLine());

        while (T.length() > S.length()) {
            int lastCharIndex = T.length() - 1;

            if (T.charAt(lastCharIndex) == 'A') {
                T.deleteCharAt(lastCharIndex);
            }
            else if (T.charAt(lastCharIndex) == 'B') {
                T.deleteCharAt(lastCharIndex);
                T.reverse();
            }
        }

        if (S.equals(T.toString())) {
            System.out.println(1);
        }else {
            System.out.println(0);
        }
    }
}
