import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static int Start;
    static int Target;
    static Map<Integer, List<Vertex>> graph = new HashMap<>();
    static int[] dist;

    static class Vertex implements Comparable<Vertex> {
        int node;
        int weight;

        public Vertex(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int a = s[0];
            int b = s[1];
            int w = s[2];

            graph.computeIfAbsent(a, val -> new ArrayList<>()).add(new Vertex(b, w));
        }

        int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Start = s[0];
        Target = s[1];

        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        System.out.println(dijkstra(Start, Target));
    }

    static int dijkstra(int start, int target) {
        Queue<Vertex> queue = new PriorityQueue<>();

        queue.add(new Vertex(start, 0));
        dist[start] = 0;

        while (!queue.isEmpty()) {
            Vertex cur = queue.poll();

            if (cur.weight > dist[cur.node]) {
                continue;
            }

            for (Vertex next : graph.getOrDefault(cur.node, new ArrayList<>())) {
                if (cur.weight + next.weight < dist[next.node]) {
                    dist[next.node] = cur.weight + next.weight;
                    queue.add(new Vertex(next.node, cur.weight + next.weight));
                }
            }
        }

        return dist[target];
    }
}
