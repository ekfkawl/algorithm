import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {

    static int N;
    static int[] craneWeights;
    static int M;
    static int[] boxWeights;

    public static class Crane {
        boolean used;
        int weight;

        public Crane(boolean used, int weight) {
            this.used = used;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        craneWeights = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();

        M = Integer.parseInt(br.readLine());
        boxWeights = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();

        List<Crane> cranes = Arrays.stream(craneWeights)
                .mapToObj(weight -> new Crane(false, weight))
                .collect(Collectors.toList());

        List<Integer> boxes = Arrays.stream(boxWeights).boxed().collect(Collectors.toList());

        int minute = 0;
        while (!boxes.isEmpty()) {
            int before = boxes.size();
            for (Crane crane : cranes) {
                if (crane.used) {
                    continue;
                }

                for (int i = 0; i < boxes.size(); i++) {
                    if (crane.weight >= boxes.get(i)) {
                        crane.used = true;
                        boxes.remove(i);
                        break;
                    }
                }
            }

            if (boxes.size() == before) {
                System.out.println(-1);
                return;
            }

            minute++;

            for (Crane crane : cranes) {
                crane.used = false;
            }
        }

        System.out.println(minute);
    }

}
