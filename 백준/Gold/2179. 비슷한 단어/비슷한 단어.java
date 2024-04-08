
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        int maxPrefix = 0;
        String s = "";
        String t = "";

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int prefixLength = getPrefixLength(words[i], words[j]);
                if (prefixLength > maxPrefix) {
                    maxPrefix = prefixLength;
                    s = words[i];
                    t = words[j];
                }
            }
        }

        System.out.println(s);
        System.out.println(t);
    }

    private static int getPrefixLength(String s, String t) {
        int length = Math.min(s.length(), t.length());
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                return i;
            }
        }
        return length;
    }
}
