
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static int N;
    static int E;

    static List<List<Integer>> graph1 = new ArrayList<>();
    static List<List<Integer>> graph2 = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = v[0];
        E = v[1];



        for (int i = 0; i <= N; i++) {
            graph1.add(new ArrayList<>());
            graph2.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            graph1.get(v[0]).add(v[1]);
            graph2.get(v[1]).add(v[0]);
        }

        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (dfs(graph1, i) + dfs(graph2, i) == N + 1) { // 현재 노드를 두번 탐색하므로 n+1
                count++;
            }
        }

        System.out.println(count);

    }

    static int dfs(List<List<Integer>> graph, int cur) {
        boolean[] visited = new boolean[N+1];
        return dfs(visited, graph, cur);
    }

    static int dfs(boolean[] visited, List<List<Integer>> graph, int cur) {
        visited[cur] = true;

        int res = 1;

        for (int next : graph.get(cur)) {
            if (visited[next]) {
                continue;
            }

            res += dfs(visited, graph, next);
        }

        return res;
    }
}
