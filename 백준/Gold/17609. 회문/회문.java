import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static String[] words;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();

            Result res = isPalindrome(0, words[i].length() - 1, words[i].toCharArray());
            if (res.isPalindrome) {
                System.out.println(0);
            } else if (likePalindrome(res.l, res.r, words[i].toCharArray())) {
                System.out.println(1);
            } else {
                System.out.println(2);
            }

        }

    }

    static Result isPalindrome(int left, int right, char[] word) {
        while (left < right) {
            if (word[left] != word[right]) {
                return new Result(left, right, false);
            }
            left++;
            right--;
        }

        return new Result(-1, -1, true);
    }

    static boolean likePalindrome(int left, int right, char[] word) {
        return isPalindrome(left, right-1, word).isPalindrome || isPalindrome(left+1, right, word).isPalindrome;
    }

    static class Result {
        int l;
        int r;
        boolean isPalindrome;

        public Result(int l, int r, boolean isPalindrome) {
            this.l = l;
            this.r = r;
            this.isPalindrome = isPalindrome;
        }
    }
}
