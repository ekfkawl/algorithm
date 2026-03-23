import java.util.*;

class Solution {
   static int INF = Integer.MAX_VALUE / 2;
    static int[][] dist;

    public static int solution(int n, int s, int a, int b, int[][] fares) {

        dist = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        
        for (int[] fare : fares) {
            int l = fare[0];
            int r = fare[1];
            int w = fare[2];

            dist[l][r] = Math.min(dist[l][r], w);
            dist[r][l] = Math.min(dist[r][l], w);
        }


        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if (dist[s][i] == INF) {
               continue; 
            }
            if (dist[i][a] == INF) {
                continue;
            }
            if (dist[i][b] == INF) {
                continue;
            }
            min = Math.min(min, dist[s][i] + dist[i][a] + dist[i][b]);
        }


        return min;
    }
}