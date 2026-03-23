import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int E;
    static int[] Targets;
    static int[][] dist;
    static Map<Integer, List<Vertex>> graph = new HashMap<>();
    static int MinWeight = Integer.MAX_VALUE;
    static int INF = Integer.MAX_VALUE / 2;

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

        int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = s[0];
        E = s[1];

        dist = new int[N+1][N+1];

        for (int i = 0; i < E; i++) {
            s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int a = s[0];
            int b = s[1];
            int w = s[2];

            graph.computeIfAbsent(a, val -> new ArrayList<>()).add(new Vertex(b, w));
            graph.computeIfAbsent(b, val -> new ArrayList<>()).add(new Vertex(a, w));
        }

        Targets = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        perm(0, Targets, new boolean[Targets.length], new int[Targets.length]);
        System.out.println(MinWeight == INF ? -1 : MinWeight);
    }

    static int[] dijkstra(int start) {
        int[] dist = new int[N+1];
        Arrays.fill(dist, INF);

        Queue<Vertex> queue = new PriorityQueue<>();
        queue.add(new Vertex(start, 0));
        dist[start] = 0;

        while (!queue.isEmpty()) {
            Vertex cur = queue.poll();

            if (cur.weight > dist[cur.node]) {
                continue;
            }

            for (Vertex next : graph.getOrDefault(cur.node, new ArrayList<>())) {
                int w = cur.weight + next.weight;
                if (dist[next.node] > w) {
                    dist[next.node] = w;
                    queue.add(new Vertex(next.node, w));
                }
            }
        }

        return dist;
    }

    static int calcWeight(int[] arr) {
        int prev = 1;
        int weight = 0;

        for (int next: arr) {
            dist[prev] = dijkstra(prev);
            if (dist[prev][next] >= INF) {
                return INF;
            }
            weight += dist[prev][next];
            prev = next;
        }

        dist[prev] = dijkstra(prev);
        if (dist[prev][N] >= INF) {
            return INF;
        }
        weight += dist[prev][N];

        return weight;
    }

    static void perm(int depth, int[] arr, boolean[] visited, int[] out) {
        if (depth == Targets.length) {
            MinWeight = Math.min(MinWeight, calcWeight(out));
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                out[depth] = arr[i];
                perm(depth + 1, arr, visited, out);
                visited[i] = false;
            }
        }
    }
}
