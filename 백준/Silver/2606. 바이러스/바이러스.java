import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    static int N;
    static int M;
    static Map<Integer, List<Integer>> graph = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            String[] s = br.readLine().split(" ");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);

            graph.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }

        System.out.println(dfs(1, new boolean[N+1]) - 1);
    }

    static int dfs(int node, boolean[] visited) {
        visited[node] = true;
        int cnt = 1;

        for (int next : graph.getOrDefault(node, new ArrayList<>())) {
            if (!visited[next]) {
                cnt += dfs(next, visited);
            }
        }

        return cnt;
    }
}
