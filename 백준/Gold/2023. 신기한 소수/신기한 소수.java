
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static int N;

    static ArrayList<Integer> list = new ArrayList<>();

    static int[] primeEntry = {2, 3, 5, 7};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());


        for (int i : primeEntry) {
            bt(1, i);
        }

        for (Integer v : list) {
            System.out.println(v);
        }

    }

    // N: 2
    // 1 11 13 17 19

    public static void bt(int depth, int n) {
        if (depth == N) {
            list.add(n);
            return;
        }

        for (int i = 1; i <= 9; i+=2) {
            int next = n * 10 + i;

            if (isPrime(next)) {
                bt(depth + 1, next);
            }

        }
    }

    public static boolean isPrime(int n) {
        if (n <= 2) {
            return false;
        }

        if (n % 2 == 0) {
            return false;
        }

        int sqrtN = (int) Math.sqrt(n);
        for (int i = 3; i <= sqrtN; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

}
