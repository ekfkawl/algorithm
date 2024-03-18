
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int K;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        if (K >= N) {
            System.out.println(0);
            return;
        }

        int[] sensors = new int[N];
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < N; i++) {
            sensors[i] = arr[i];
        }

        Arrays.sort(sensors);

        int[] distances = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            distances[i] = sensors[i + 1] - sensors[i];
        }

        Arrays.sort(distances);

        int sum = 0;
        for (int i = 0; i < N - K; i++) {
            sum += distances[i];
        }

        System.out.println(sum);
    }
}
