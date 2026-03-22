import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 첫째 줄에 도시의 개수 n이 주어지고 둘째 줄에는 버스의 개수 m이 주어진다. 그리고 셋째 줄부터 m+2줄까지 다음과 같은 버스의 정보가 주어진다.
public class Main {
    static int n; // city
    static int m; // bus

    static int[][] dist;

    static int INF = 100000001;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        dist = new int[n+1][n+1];

        for (int i = 1; i < n+1; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            String[] s = br.readLine().split(" ");
            int v1 = Integer.parseInt(s[0]);
            int v2 = Integer.parseInt(s[1]);
            int weight = Integer.parseInt(s[2]);
            dist[v1][v2] = Math.min(dist[v1][v2], weight);
        }

        for (int m = 1; m <= n; m++) { // 중간
            for (int s = 1; s <= n; s++) { // 시작
                for (int e = 1; e <= n; e++) { // 끝
                    dist[s][e] = Math.min(dist[s][e], dist[s][m] + dist[m][e]);
                }
            }
        }


        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int v = dist[i][j];
                if (dist[i][j] == INF) {
                    v = 0;
                }
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }
}
