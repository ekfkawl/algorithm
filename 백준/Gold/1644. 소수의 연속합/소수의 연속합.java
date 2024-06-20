import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int N;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        if (N == 2) {
            System.out.println(1);
            return;
        }

        List<Integer> primes = new ArrayList<>();
        primes.add(2);

        int n = 3;
        while (n <= N) {
            if (isPrime(n)) {
                primes.add(n);
            }

            n += 2;
        }

        int l = 0;
        int r = 0;

        int cur = 0;
        int count = 0;

        while (true) {
            if (cur < N) {
                if (r == primes.size()) {
                    break;
                }
                
                cur += primes.get(r++);
            }else {
                cur -= primes.get(l++);
            }

            if (cur == N) {
                count++;
            }
        }


        System.out.println(count);
    }

    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }

        if (n == 2) {
            return true;
        }

        if (n % 2 == 0) {
            return false;
        }

        int sqrt = (int) Math.sqrt(n);
        for (int i = 3; i <= sqrt; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}
