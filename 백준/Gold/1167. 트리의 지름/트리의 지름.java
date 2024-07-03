import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static int N;

    static List<List<Vertex>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            int[] v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int vertex = v[0];
            int linkedVertex = 0;
            int weight = 0;

            for (int j = 1; j < v.length-1; j+=2) {
                linkedVertex = v[j];
                weight = v[j+1];

                graph.get(vertex).add(new Vertex(linkedVertex, weight));
            }

        }
        var vertex = dfs(new Vertex(1, 0));
        int weight = dfs(new Vertex(vertex.node, 0)).weight;

        System.out.println(weight);
    }

    static Vertex dfs(Vertex vertex) {
        boolean[] visited = new boolean[N+1];
        return dfs(vertex, visited);
    }
    
    static Vertex dfs(Vertex vertex, boolean[] visited) {
        visited[vertex.node] = true;

        Vertex res = vertex;

        List<Vertex> vertices = graph.get(vertex.node);
        for (Vertex v : vertices) {
            if (!visited[v.node]) {
                Vertex next = dfs(new Vertex(v.node, vertex.weight + v.weight), visited);

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
