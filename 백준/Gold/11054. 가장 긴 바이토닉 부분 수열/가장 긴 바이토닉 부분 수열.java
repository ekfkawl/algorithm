
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static int[] arr;

    static int[] lis;
    static int[] lds;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        lis = new int[N];
        lds = new int[N];

        lis();
        lds();

        int max = lis[0] + lds[0] - 1;
        for (int i = 1; i < N; i++) {
            max = Math.max(max, lis[i] + lds[i] - 1);
        }
        System.out.println(max);
    }


    public static void lis() {
        for (int i = 0; i < N; i++) {
            lis[i] = 1;

            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && lis[i] < lis[j]+1) {
                    lis[i] = lis[j]+1;
                }
            }
        }
    }

    public static void lds() {
        for (int i = N - 1; i >= 0; i--) {
            lds[i] = 1;
            for (int j = N - 1; j > i; j--) {
                if (arr[i] > arr[j] && lds[i] < lds[j]+1) {
                    lds[i] = lds[j]+1;
                }
            }
        }
    }
}
