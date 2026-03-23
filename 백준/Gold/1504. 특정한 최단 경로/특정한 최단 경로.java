import java.io.*;
import java.util.*;

public class Main  {

    static int N;
    static int E;
    static int[] Targets;
    static int[][] dist;
    static int MinWeight = Integer.MAX_VALUE;
    static int INF = Integer.MAX_VALUE / 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = s[0];
        E = s[1];

        dist = new int[N+1][N+1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 0; i < E; i++) {
            s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int a = s[0];
            int b = s[1];
            int w = s[2];

            dist[a][b] = Math.min(dist[a][b], w);
            dist[b][a] = Math.min(dist[b][a], w);
        }

        Targets = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        perm(0, Targets, new boolean[Targets.length], new int[Targets.length]);
        System.out.println(MinWeight == INF ? -1 : MinWeight);
    }

    static int calcWeight(int[] arr) {
        int prev = 1;
        int weight = 0;

        for (int next: arr) {
            if (dist[prev][next] >= INF) {
                return INF;
            }
            weight += dist[prev][next];
            prev = next;
        }

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
