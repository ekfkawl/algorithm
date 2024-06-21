

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    static int N;
    static int[] arr;

    static boolean[] visited;
    static boolean[] currentPath;

    static Queue<Integer> res = new PriorityQueue<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        visited = new boolean[N + 1];
        currentPath = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }

        System.out.println(res.size());
        while (!res.isEmpty()) {
            System.out.println(res.poll());
        }

    }

    public static void dfs(int current) {
        visited[current] = true;
        currentPath[current] = true;

        int next = arr[current];

        if (!visited[next]) {
            dfs(next);
        }else if (currentPath[next]) {
            int last = next;
            res.add(last);

            while (last != current) {
                last = arr[last];
                res.add(last);
            }
        }

        currentPath[current] = false;

    }
}
