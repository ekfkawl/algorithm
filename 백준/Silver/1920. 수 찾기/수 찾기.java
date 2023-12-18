
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String[] s = br.readLine().split(" ");
        Set<Integer> map = new HashSet<>();

        for (int i = 0; i < n; i++) {
            map.add(Integer.parseInt(s[i]));
        }

        int m = Integer.parseInt(br.readLine());
        s = br.readLine().split(" ");

        for (int i = 0; i < m; i++) {
            int idx = Integer.parseInt(s[i]);

            if (map.contains(idx)) {
                System.out.println(1);
            }else {
                System.out.println(0);
            }
        }


        br.close();
    }


}
