import java.util.*;

class Solution {
    static Map<Integer, List<Vertex>> graph = new HashMap<>();
    static int[] dist;
    static int INF = Integer.MAX_VALUE / 2;
    
    static class Vertex implements Comparable<Vertex> {
        int node;
        int weight;
        
        public Vertex(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
        
        public int compareTo(Vertex o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    
    public static int solution(int N, int[][] road, int K) {
        
        dist = new int[N+1];
        Arrays.fill(dist, INF);
        
        for (int[] v: road) {
            int a = v[0];
            int b = v[1];
            int w = v[2];
            
            graph.computeIfAbsent(a, val -> new ArrayList<>()).add(new Vertex(b, w));
            graph.computeIfAbsent(b, val -> new ArrayList<>()).add(new Vertex(a, w));
        }
        
        int cnt = 0;
        dijkstra(1);
        for (int w: dist) {
            if (w <= K) {
                cnt++;
            }
        }
        
        return cnt;
    }
    
    static void dijkstra(int start) {
        Queue<Vertex> queue = new PriorityQueue<>();
        
        queue.add(new Vertex(start, 0));
        dist[start] = 0;
        
        while (!queue.isEmpty()) {
            Vertex cur = queue.poll();
            
            if (cur.weight < dist[cur.node]) {
                continue;
            }
            
            for (Vertex next: graph.getOrDefault(cur.node, new ArrayList<>())) {
                if (cur.weight + next.weight < dist[next.node]) {
                    dist[next.node] = cur.weight + next.weight;
                    queue.add(new Vertex(next.node, cur.weight + next.weight));
                }
            }
        }
    }
}