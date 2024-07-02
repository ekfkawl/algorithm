
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
        var a = dfs(new Vertex(1, 0), 0);

        visited = new boolean[N+1];
        var b = dfs(new Vertex(a.node, 0), 0);
        System.out.println(b.weight);

    }

    static Vertex dfs(Vertex vertex, int totalWeight) {
        visited[vertex.node] = true;

        Vertex res = new Vertex(vertex.node, totalWeight);

        List<Vertex> vertices = graph.get(vertex.node);
        for (Vertex v : vertices) {
            if (!visited[v.node]) {
                Vertex next = dfs(v, totalWeight + v.weight);

                if (next.weight > res.weight) {
                    res = next;
                }
            }
        }

        return res;
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
