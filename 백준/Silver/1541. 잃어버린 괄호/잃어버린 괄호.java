import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] sub = br.readLine().split("-");

        int result = 0;
        for (int i = 0; i < sub.length; i++) {
            int tmp = 0;

            String[] add = sub[i].split("\\+");
            for (String num : add) {
                tmp += Integer.parseInt(num);
            }

            if (i == 0) {
                result = tmp;
            }else {
                result -= tmp;
            }
        }

        System.out.println(result);
    }
}
