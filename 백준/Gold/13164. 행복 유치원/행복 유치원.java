
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] keys = new int[N];
        int[] diffs = new int[N-1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            keys[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N-1; i++) {
            diffs[i] = Math.abs(keys[i] - keys[i+1]);
        }

        Arrays.sort(diffs);

        int answer = 0;
        // 가장 큰 K-1개의 차이를 제외한 나머지 합 계산
        for (int i = 0; i < N - K; i++) {
            answer += diffs[i];
        }

        System.out.println(answer);
    }
}
