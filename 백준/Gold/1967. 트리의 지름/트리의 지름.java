
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static int N;

    static List<List<Vertex>> graph = new ArrayList<>();

    static boolean[] visited;

    static Vertex res = new Vertex(0, 0);

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N-1; i++) {
            int[] v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int parent = v[0];
            int child = v[1];
            int weight = v[2];

            graph.get(parent).add(new Vertex(child, weight));
            graph.get(child).add(new Vertex(parent, weight));
        }

        visited = new boolean[N+1];
        dfs(new Vertex(1, 0), 0);

        visited = new boolean[N+1];
        res = new Vertex(res.node, 0);
        dfs(new Vertex(res.node, 0), 0);

        System.out.println(res.weight);
    }

    static void dfs(Vertex vertex, int totalWeight) {
        if (totalWeight > res.weight) {
            res = new Vertex(vertex.node, totalWeight);
        }

        visited[vertex.node] = true;

        List<Vertex> vertices = graph.get(vertex.node);
        for (Vertex v : vertices) {
            if (!visited[v.node]) {
                dfs(v, totalWeight + v.weight);
            }
        }
    }

    static class Vertex {
        int node;
        int weight;

        public Vertex(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}
