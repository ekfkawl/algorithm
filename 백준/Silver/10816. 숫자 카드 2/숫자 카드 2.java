import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    static int N;
    static int[] cards;
    static int M;
    static int[] counts;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        cards = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        M = Integer.parseInt(br.readLine());
        counts = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int card : cards) {
            map.put(card, map.getOrDefault(card, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        for (int count : counts) {
            sb.append(map.getOrDefault(count, 0)).append(" ");
        }

        System.out.println(sb);

    }
}
