
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    static int N;

    static int[][] graph;
    static int[][][] tables;
    static List<Position> blankBoards = new ArrayList<>();
    static List<List<Position>> puzzleEntries = new ArrayList<>();

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};


    public static class Position {
        int y;
        int x;
        int size;
        boolean visited;

        public Position(int y, int x, int size) {
            this.y = y;
            this.x = x;
            this.size = size;
        }
    }



    public static int solution(int[][] game_board, int[][] table) {
        N = game_board.length;
        graph = new int[N][N];
        tables = new int[4][N][N];

        for (int i = 0; i < 4; i++) {
            puzzleEntries.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            graph[i] = Arrays.copyOf(game_board[i], N);
            tables[0][i] = Arrays.copyOf(table[i], N);
        }

        // init rotate 90` 180` 270`
        for (int i = 1; i <= 3; i++) {
            tables[i] = rotateGraph90(tables[i - 1]);
        }

        // marking
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (graph[i][j] == 0) {
                    graph[i][j] = 2;
                    int size = markedPuzzleEntryPosition(graph, i, j, 0, new boolean[N][N]) + 1;
                    blankBoards.add(new Position(i, j, size));
                }

                if (tables[0][i][j] == 1) {
                    tables[0][i][j] = 2;
                    int size = markedPuzzleEntryPosition(tables[0], i, j, 1, new boolean[N][N]) + 1;
                    puzzleEntries.get(0).add(new Position(i, j, size));
                }
            }
        }

        for (int i = 1; i <= 3; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (tables[i][j][k] == 1) {
                        tables[i][j][k] = 2;
                        int size = markedPuzzleEntryPosition(tables[i], j, k, 1, new boolean[N][N]) + 1;
                        puzzleEntries.get(i).add(new Position(j, k, size));
                    }
                }
            }
        }

        return matchingPuzzle();
    }

    public static int matchingPuzzle() {
        int res = 0;

        for (Position blankBoard : blankBoards) {
            loop:
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < puzzleEntries.get(j).size(); k++) {
                    Position puzzleEntry = puzzleEntries.get(j).get(k);

                    if (!blankBoard.visited && tables[j][puzzleEntry.y][puzzleEntry.x] != 0) {
                        int count = matchPuzzle(blankBoard, tables[j], puzzleEntry, new boolean[N][N]);

                        if (count == puzzleEntry.size && count == blankBoard.size) {
                            blankBoard.visited = true;
                            deletePuzzle(tables[j], puzzleEntry, new boolean[N][N]);

                            int next = j + 1;
                            int r = 0;

                            Position nextPosition = new Position(puzzleEntry.x, N - 1 - puzzleEntry.y, 0);

                            while (r < 3) {
                                if (next > 3) {
                                    next = 0;
                                }

                                deletePuzzle(tables[next], nextPosition, new boolean[N][N]);
                                nextPosition = new Position(nextPosition.x, N - 1 - nextPosition.y, 0);

                                r++;
                                next++;
                            }

                            res += count;
                            System.out.println(j + " / "  + blankBoard.y + ", " + blankBoard.x + " / " + puzzleEntry.y + ", " + puzzleEntry.x);
                            break loop;
                        }
                    }
                }
            }
        }
        return res;
    }

    public static void deletePuzzle(int[][] dest, Position puzzle, boolean[][] visited) {
        visited[puzzle.y][puzzle.x] = true;
        dest[puzzle.y][puzzle.x] = 0;

        for (int i = 0; i < 4; i++) {
            int ny = puzzle.y + dy[i];
            int nx = puzzle.x + dx[i];

            if (isSafeGraphIndex(ny, nx) && dest[ny][nx] > 0 && !visited[ny][nx]) {
                deletePuzzle(dest, new Position(ny, nx, -1), visited);
            }
        }
    }

    public static int matchPuzzle(Position blankBoard, int[][] table, Position puzzle, boolean[][] visited) {
        int res = 0;

        int by = blankBoard.y;
        int bx = blankBoard.x;

        int py = puzzle.y;
        int px = puzzle.x;


        if (graph[by][bx] == table[py][px]) {
            res = 1;
        }

        for (int i = 0; i < 4; i++) {
            int nby = by + dy[i];
            int nbx = bx + dx[i];

            int npy = py + dy[i];
            int npx = px + dx[i];

            if (isSafeGraphIndex(nby, nbx) && isSafeGraphIndex(npy, npx) && graph[nby][nbx] == 3 && table[npy][npx] == 3 && !visited[nby][nbx]) {
                visited[nby][nbx] = true;

                Position nextBlankBoard = new Position(nby, nbx, blankBoard.size);
                Position nextPuzzle = new Position(npy, npx, puzzle.size);
                res += matchPuzzle(nextBlankBoard, table, nextPuzzle, visited);
            }
        }

        return res;
    }

    public static int markedPuzzleEntryPosition(int[][] dest, int y, int x, int eq, boolean[][] visited) {
        int res = 0;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (isSafeGraphIndex(ny, nx) && dest[ny][nx] == eq && !visited[ny][nx]) {
                dest[ny][nx] = 3;
                visited[ny][nx] = true;
                res += markedPuzzleEntryPosition(dest, ny, nx, eq, visited) + 1;
            }
        }

        return res;
    }

    public static int[][] rotateGraph90(int[][] src) {
        int[][] res = new int[N][N];
        for (int i = 0; i < N; i++) {
            res[i] = Arrays.copyOf(src[i], N);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                res[j][N - 1 - i] = src[i][j];
            }
        }

        return res;
    }

    public static boolean isSafeGraphIndex(int y, int x) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}
