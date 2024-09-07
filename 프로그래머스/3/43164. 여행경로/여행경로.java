

import java.util.*;

public class Solution {
    static int N;
    static List<List<Ticket>> graph = new ArrayList<>();
    static Queue<String> results = new PriorityQueue<>();

    static class Ticket implements Comparable<Ticket> {
        String airplane;
        int index;
        boolean visited;

        public Ticket(String airplane, int index, boolean visited) {
            this.airplane = airplane;
            this.index = index;
            this.visited = visited;
        }

        @Override
        public int compareTo(Ticket o) {
            return this.airplane.compareTo(o.airplane);
        }
    }

    public static String[] solution(String[][] tickets) {
        N = tickets.length;

        for (int i = 0; i <= airplaneToIndex("ZZZ"); i++) {
            graph.add(new ArrayList<>());
        }

        for (String[] ticket : tickets) {
            int a = airplaneToIndex(ticket[0]);
            int b = airplaneToIndex(ticket[1]);

            Ticket dest = new Ticket(ticket[1], b, false);
            graph.get(a).add(dest);
        }

        Ticket ticket = new Ticket("ICN", airplaneToIndex("ICN"), true);
        List<Ticket> path = new ArrayList<>();
        dfs(ticket, 1, path);

        String bestPath = "ICN" + results.poll();
        return bestPath.split(" ");
    }

    static void dfs(Ticket cur, int depth, List<Ticket> path) {

        if (depth == N + 1) {
            StringBuilder sb = new StringBuilder();
            for (Ticket ticket : path) {
                sb.append(" ").append(ticket.airplane);
            }
            results.add(sb.toString());
            return;
        }

        for (Ticket next : graph.get(cur.index)) {
            if (!next.visited) {
                path.add(next);
                next.visited = true;
                dfs(next, depth + 1, path);
                path.remove(next);
                next.visited = false;
            }
        }
    }


    static int airplaneToIndex(String airplane) {
        int res = 0;

        for (int i = 0; i < airplane.length(); i++) {
            res += airplane.charAt(i) * (int)Math.pow(3, i);
        }

        return res;
    }
}
