

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static long[] classes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        classes = new long[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int timeCount = Integer.parseInt(st.nextToken());

            for (int j = 0; j < timeCount; j++) {
                classes[i] |= 1L << Long.parseLong(st.nextToken());
            }
        }


        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int timeCount = Integer.parseInt(st.nextToken());

            long studentFreeTime = 0;
            for (int j = 0; j < timeCount; j++) {
                studentFreeTime |= 1L << Long.parseLong(st.nextToken());
            }

            int classCount = 0;
            for (int k = 0; k < N; k++) {
                if ((classes[k] & studentFreeTime) == classes[k]) {
                    classCount++;
                }
            }

            System.out.println(classCount);
        }
    }
}