import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        
        Arrays.sort(arr);
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            long target = arr[i];
            int left = 0;
            int right = n - 1;
            
            while (left < right) {
                if (left == i) left++; 
                else if (right == i) right--; 
                else {
                    long sum = arr[left] + arr[right];
                    
                    if (sum == target) {
                        count++;
                        break;
                    }
                    if (sum < target) left++;
                    else right--;
                }
            }
        }
        
        System.out.println(count);
    }
}
