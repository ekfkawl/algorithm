import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] distances = new int[N+1];

        int totalDistance = 0;
        for (int i = 1; i <= N; i++) {
            distances[i] = Integer.parseInt(br.readLine());
            totalDistance += distances[i];
        }

        int maxDistance = 0;
        int sum = 0;

        for (int start = 1, end = 1; start <= N; start++) {
            while (sum < totalDistance / 2) {
                sum += distances[end];
                end = (end % N) + 1;
            }

            maxDistance = Math.max(maxDistance, Math.min(sum, totalDistance - sum));
            sum -= distances[start];
        }

        System.out.println(maxDistance);
    }
}
