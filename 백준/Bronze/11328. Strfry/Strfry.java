
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split(" ");
            String a = s[0];
            String b = s[1];

            char[] l = a.toCharArray();
            char[] r = b.toCharArray();
            Arrays.sort(l);
            Arrays.sort(r);

            System.out.println(Arrays.toString(l).equals(Arrays.toString(r)) ? "Possible" : "Impossible");
        }
    }
}
