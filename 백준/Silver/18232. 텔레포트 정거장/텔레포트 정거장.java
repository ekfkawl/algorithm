
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static int S;
    static int E;

    static int[] dist;

    static boolean[] visited;

    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);

        s = br.readLine().split(" ");
        S = Integer.parseInt(s[0]);
        E = Integer.parseInt(s[1]);

        visited = new boolean[N+1];

        for (int i = 0; i < N+1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            s = br.readLine().split(" ");
            int v1 = Integer.parseInt(s[0]);
            int v2 = Integer.parseInt(s[1]);
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }

        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        bfs(S);
        System.out.println(dist[E]);
    }


    public static void bfs(int start) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        dist[start] = 0;
        visited[start] = true;
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (cur == E) {
                break;
            }

            for (int i : graph.get(cur)) {
                if (!isValidIndex(i) || visited[i]) {
                    continue;
                }

                queue.add(i);
                dist[i] = dist[cur] + 1;
                visited[i] = true;
            }

            if (isValidIndex(cur + 1) && !visited[cur + 1]) {
                queue.add(cur + 1);
                dist[cur + 1] = dist[cur] + 1;
                visited[cur + 1] = true;
            }

            if (isValidIndex(cur - 1) && !visited[cur - 1]) {
                queue.add(cur - 1);
                dist[cur - 1] = dist[cur] + 1;
                visited[cur - 1] = true;
            }
        }

    }

    public static boolean isValidIndex(int idx) {
        return idx >= 1 && idx <= N;
    }

}
