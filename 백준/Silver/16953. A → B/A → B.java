
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        int a = Integer.parseInt(s[0]);
        int b = Integer.parseInt(s[1]);
        int count = 1;

        while (b > a) {
            if (b % 2 == 0) {
                b /= 2;

            }else if (b % 10 == 1) {
                b /= 10;

            }else {
                System.out.println(-1);
                return;
            }
            count++;
        }

        if (a == b) {
            System.out.println(count);
        } else {
            System.out.println(-1);
        }
    }
}
