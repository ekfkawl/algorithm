
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static int start;

    static Map<Integer, List<Integer>> graph = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        start = Integer.parseInt(s[2]);

        for (int i = 0; i < M; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int a = arr[0];
            int b = arr[1];

            graph.computeIfAbsent(a, v -> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b, v -> new ArrayList<>()).add(a);
        }

        System.out.println(dfs(start, new boolean[N+1]).toString().trim());
        System.out.println(bfs(start, new boolean[N+1]).toString().trim());
    }

    static StringBuilder dfs(int node, boolean[] visited) {
        visited[node] = true;
        StringBuilder sb = new StringBuilder().append(node).append(" ");

        if (graph.containsKey(node)) {
            Collections.sort(graph.get(node));
            for (int next : graph.get(node)) {
                if (!visited[next]) {
                    sb.append(dfs(next, visited));
                }
            }
        }

        return sb;
    }

    static StringBuilder bfs(int node, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        visited[node] = true;

        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            sb.append(cur).append(" ");

            if (graph.containsKey(node)) {
                Collections.sort(graph.get(cur));
                for (int next : graph.get(cur)) {
                    if (!visited[next]) {
                        queue.add(next);
                        visited[next] = true;
                    }
                }
            }
        }

        return sb;
    }
}
