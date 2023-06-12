import java.util.*;

public class HorseJumpChessboard {
    private static final int BOARD_SIZE = 8;
    private static final int[][] MOVES = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}};

    public static void main(String[] args) {
        int startX = 0; // 起始位置的横坐标
        int startY = 0; // 起始位置的纵坐标
        int[][] chessboard = new int[BOARD_SIZE][BOARD_SIZE];

        List<int[]> path = findHorsePath(startX, startY, chessboard);
        if (path != null) {
            System.out.println("找到跳马的路径：");
            for (int[] position : path) {
                System.out.println("(" + position[0] + ", " + position[1] + ")");
            }
        } else {
            System.out.println("未找到跳马的路径");
        }
    }

    public static List<int[]> findHorsePath(int startX, int startY, int[][] chessboard) {
        List<int[]> path = new ArrayList<>();
        path.add(new int[]{startX, startY});
        chessboard[startX][startY] = 1;

        if (dfs(startX, startY, chessboard, path, 2)) {
            return path;
        } else {
            return null;
        }
    }

    private static boolean dfs(int x, int y, int[][] chessboard, List<int[]> path, int step) {
        if (step > BOARD_SIZE * BOARD_SIZE) {
            return true; // 所有位置都已经访问过，找到路径
        }

        List<int[]> nextMoves = getNextMoves(x, y, chessboard);
        for (int[] move : nextMoves) {
            int nextX = move[0];
            int nextY = move[1];

            path.add(new int[]{nextX, nextY});
            chessboard[nextX][nextY] = step;

            if (dfs(nextX, nextY, chessboard, path, step + 1)) {
                return true;
            }

            path.remove(path.size() - 1);
            chessboard[nextX][nextY] = 0;
        }

        return false;
    }

    private static List<int[]> getNextMoves(int x, int y, int[][] chessboard) {
        List<int[]> nextMoves = new ArrayList<>();

        for (int[] move : MOVES) {
            int nextX = x + move[0];
            int nextY = y + move[1];

            if (isValidMove(nextX, nextY, chessboard)) {
                nextMoves.add(new int[]{nextX, nextY});
            }
        }

        return nextMoves;
    }

    private static boolean isValidMove(int x, int y, int[][] chessboard) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE && chessboard[x][y] == 0;
    }
}

