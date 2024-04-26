
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Pair[] arr = new Pair[n];

        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(br.readLine());
            arr[i] = new Pair(value, i);
        }

        Arrays.sort(arr, Comparator.comparingInt(pair -> pair.value));

        int maxMovement = 0;
        for (int i = 0; i < n; i++) {
            int movement = arr[i].originalIndex - i;

            if (movement > maxMovement) {
                maxMovement = movement;
            }
        }

        System.out.println(maxMovement + 1);
    }

    static class Pair {
        int value;
        int originalIndex;

        public Pair(int value, int originalIndex) {
            this.value = value;
            this.originalIndex = originalIndex;
        }
    }
}
