

import java.util.*;

public class Solution {

    static List<List<Integer>> graph = new ArrayList<>();
    static List<List<Integer>> reverseGraph = new ArrayList<>();
    static boolean[] visited;


    public static int solution(int n, int[][] results) {

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        for (int[] result : results) {
            graph.get(result[0]).add(result[1]);
            reverseGraph.get(result[1]).add(result[0]);
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            visited = new boolean[n + 1];
            int w = wDfs(i);

            visited = new boolean[n + 1];
            int l = lDfs(i);

            if (w + l == n - 1) {
                count++;
            }
        }

        return count;
    }

    static int wDfs(int cur) {
        int res = 0;

        visited[cur] = true;

        for (int next : graph.get(cur)) {
            if (!visited[next]) {
                res += wDfs(next) + 1;
            }
        }

        return res;
    }

    static int lDfs(int cur) {
        int res = 0;

        visited[cur] = true;

        for (int next : reverseGraph.get(cur)) {
            if (!visited[next]) {
                res += lDfs(next) + 1;
            }
        }

        return res;
    }
}
