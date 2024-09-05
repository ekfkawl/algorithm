
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int F = Integer.parseInt(br.readLine());

            List<String[]> friends = new ArrayList<>();
            Map<String, String> parent = new HashMap<>();
            Map<String, Integer> rank = new HashMap<>();
            Map<String, Integer> size = new HashMap<>();

            while (F-- > 0) {
                String[] friend = br.readLine().split(" ");
                parent.put(friend[0], friend[0]);
                parent.put(friend[1], friend[1]);
                rank.put(friend[0], 0);
                rank.put(friend[1], 0);
                size.put(friend[0], 1);
                size.put(friend[1], 1);

                friends.add(friend);
            }


            for (String[] friend : friends) {
                union(parent, rank, size, friend[0], friend[1]);
                System.out.println(size.get(find(parent, friend[0])));
            }
        }
    }

    public static String find(Map<String, String> parent, String x) {
        if (!parent.get(x).equals(x)) {
            parent.put(x, find(parent, parent.get(x)));
        }

        return parent.get(x);
    }

    public static void union(Map<String, String> parent, Map<String, Integer> rank, Map<String, Integer> size, String x, String y) {
        String rootX = find(parent, x);
        String rootY = find(parent, y);

        if (rootX.equals(rootY)) {
            return;
        }

        if (rank.get(rootX) > rank.get(rootY)) {
            parent.put(rootY, rootX);
            size.computeIfPresent(rootX, (k, v) -> v + size.get(rootY));
        }else if (rank.get(rootX) < rank.get(rootY)) {
            parent.put(rootX, rootY);
            size.computeIfPresent(rootY, (k, v) -> v + size.get(rootX));
        }else {
            parent.put(rootY, rootX);
            rank.computeIfPresent(rootX, (k, v) -> ++v);
            size.computeIfPresent(rootX, (k, v) -> v + size.get(rootY));
        }
    }
}
