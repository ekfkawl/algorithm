import java.util.*;

class Solution {
    
    static int MaxCount = Integer.MAX_VALUE;

    public static int solution(int n, int[][] wires) {
        // comb(0, 0, wires, new HashMap<>(), n);
        comb(wires, n);
        return MaxCount;
    }
    
    static int dfs(int node, boolean[] visited, Map<Integer, List<Integer>> graph) {
        int res = 1;
        visited[node] = true;

        for (int next : graph.getOrDefault(node, new ArrayList<>())) {
            if (!visited[next]) {
                res += dfs(next, visited, graph);
            }
        }

        return res;
    }
    
    static void comb(int[][] wires, int n) {
        for (int i = 0; i < wires.length; i++) {
            
            Map<Integer, List<Integer>> graph = new HashMap<>();
            for (int j = 0; j < wires.length; j++) {
                if (i == j) {
                    continue;
                }
                
                int a = wires[j][0];
                int b = wires[j][1];
                graph.computeIfAbsent(a, v -> new ArrayList<>()).add(b);
                graph.computeIfAbsent(b, v -> new ArrayList<>()).add(a);
            }
            
            boolean[] visited = new boolean[n + 1];
            int a = 0;
            int b = 0;

            for (int j = 1; j <= n; j++) {
                if (!visited[j]) {
                    a = dfs(j, visited, graph);
                    break;
                }
            }

            for (int j = 1; j <= n; j++) {
                if (!visited[j]) {
                    b = dfs(j, visited, graph);
                    break;
                }
            }
            
            MaxCount = Math.min(MaxCount, Math.abs(a - b));
        }
    }

    /*
    static void comb(int start, int depth, int[][] wires, Map<Integer, List<Integer>> graph, int n) {
        if (depth == wires.length - 1) {

            boolean[] visited = new boolean[n + 1];
            int a = 0;
            int b = 0;

            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    a = dfs(i, visited, graph);
                    break;
                }
            }

            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    b = dfs(i, visited, graph);
                    break;
                }
            }

            MaxCount = Math.min(MaxCount, Math.abs(a - b));
        }

        for (int i = start; i < wires.length; i++) {
            int a = wires[i][0];
            int b = wires[i][1];
            graph.computeIfAbsent(a, v -> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b, v -> new ArrayList<>()).add(a);

            comb(i + 1, depth + 1, wires, graph, n);

            graph.get(a).remove((Integer)b);
            graph.get(b).remove((Integer)a);
        }
    }
    */
}