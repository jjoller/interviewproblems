import java.util.HashSet;
import java.util.Set;

public class SurroundedRegions {

    public static void main(String[] args) {

        char[][] f = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X',}};

        print(f);
        System.out.println();
        solve(f);
        print(f);
    }

    public static void print(char[][] board) {

        int n = board.length;
        int m = board[0].length;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                System.out.print(board[x][y]);
            }
            System.out.print("\n");
        }

    }

    public static void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (isSurrounded(new Pos(x, y), board, new HashSet<Pos>())) {
                    board[x][y] = 'X';
                }
            }
        }
    }

    static boolean isSurrounded(Pos pos, char[][] board, Set<Pos> visited) {

        visited.add(pos);

        int n = board.length;
        int m = board[0].length;
        if (pos.x >= n)
            return false;
        if (pos.x < 0)
            return false;
        if (pos.y >= m)
            return false;
        if (pos.y < 0)
            return false;
        if (board[pos.x][pos.y] == 'X')
            return true;

        boolean left = visited.contains(pos.left()) ? true : isSurrounded(pos.left(), board, visited);
        boolean top = visited.contains(pos.top()) ? true : isSurrounded(pos.top(), board, visited);
        boolean right = visited.contains(pos.right()) ? true : isSurrounded(pos.right(), board, visited);
        boolean bottom = visited.contains(pos.bottom()) ? true : isSurrounded(pos.bottom(), board, visited);

        return left && top && right && bottom;
    }

    static class Pos implements Comparable<Pos> {
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        final int x, y;

        Pos left() {
            return new Pos(x - 1, y);
        }

        Pos top() {
            return new Pos(x, y - 1);
        }

        Pos right() {
            return new Pos(x + 1, y);
        }

        Pos bottom() {
            return new Pos(x, y + 1);
        }

        @Override
        public int hashCode() {
            return (x << 16) | y;
        }

        @Override
        public int compareTo(Pos o) {
            return this.hashCode() - o.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            } else {
                return compareTo((Pos) obj) == 0;
            }
        }
    }
}
