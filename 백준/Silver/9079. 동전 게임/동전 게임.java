
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            int state = 0;
            for (int i = 0; i < 3; i++) {
                String line = br.readLine().replace(" ", "");
                for (int j = 0; j < 3; j++) {
                    if (line.charAt(j) == 'T') { // 공백을 고려해 인덱스 조정
                        state |= (1 << (i * 3 + j));
                    }
                }
            }

            if (state == 0 || state == 0b111111111) {
                System.out.println(0);
                continue;
            }

            Queue<Integer> queue = new LinkedList<>();
            Map<Integer, Integer> distance = new HashMap<>();
            queue.add(state);
            distance.put(state, 0);

            boolean solved = false;
            while (!queue.isEmpty() && !solved) {
                int currentState = queue.poll();
                if (currentState == 0 || currentState == 0b111111111) {
                    System.out.println(distance.get(currentState));
                    solved = true;
                    break;
                }

                for (int i = 0; i < 8; i++) {
                    int nextState = flip(currentState, i);
                    if (!distance.containsKey(nextState)) {
                        distance.put(nextState, distance.get(currentState) + 1);
                        queue.add(nextState);
                    }
                }
            }

            if (!solved) {
                System.out.println(-1);
            }
        }
    }

    static int flip(int state, int index) {
        switch (index) {
            case 0: return state ^ 0b000000111; // 첫 번째 행
            case 1: return state ^ 0b000111000; // 두 번째 행
            case 2: return state ^ 0b111000000; // 세 번째 행
            case 3: return state ^ 0b001001001; // 첫 번째 열
            case 4: return state ^ 0b010010010; // 두 번째 열
            case 5: return state ^ 0b100100100; // 세 번째 열
            case 6: return state ^ 0b100010001; // 대각선 L
            case 7: return state ^ 0b001010100; // 대각선 R
        }
        return state;
    }
}
