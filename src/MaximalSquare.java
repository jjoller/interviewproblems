import java.util.HashMap;
import java.util.Map;

/**
 * https://codefights.com/interview-practice/task/mkobsYSSQo3JpvYNN
 * <p>
 * You have a 2D binary matrix that's filled with 0s and 1s. In the matrix, find the largest square that contains only 1s and return its area.
 * <p>
 * Example
 * <p>
 * For
 * <p>
 * matrix = [
 * ['1', '0', '1', '1', '1'],
 * ['1', '0', '1', '1', '1'],
 * ['1', '1', '1', '1', '1'],
 * ['1', '0', '0', '1', '0'],
 * ['1', '0', '0', '1', '0']
 * ]
 * the output should be
 * maximalSquare(matrix) = 9.
 * <p>
 * Input/Output
 * <p>
 * [execution time limit] 3 seconds (java)
 * <p>
 * [input] array.array.char matrix
 * <p>
 * Guaranteed constraints:
 * 0 ≤ matrix.length ≤ 100,
 * 1 ≤ matrix[i].length ≤ 100,
 * 0 ≤ matrix[i][j] ≤ 1.
 * <p>
 * [output] integer
 * <p>
 * An integer that represents the area of the largest square in the given matrix that is composed only of 1s.
 */
public class MaximalSquare {

    int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        if (n == 0) {
            return 0;
        }
        int m = matrix[0].length;
        int max = 0;
        Map<Integer, Integer> cache = new HashMap<>();
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (matrix[x][y] == '1') {
                    max = Math.max(max, maxSquare(x, y, matrix, cache));
                }
            }
        }
        return max * max;
    }

    int maxSquare(int x, int y, char[][] matrix, Map<Integer, Integer> cache) {
        if (x >= matrix.length) {
            return 0;
        }
        if (y >= matrix[0].length) {
            return 0;
        }
        if (matrix[x][y] != '1') {
            return 0;
        }
        if (cache.containsKey(key(x, y))) {
            return cache.get(key(x, y));
        }
        int a = maxSquare(x + 1, y + 1, matrix, cache);
        int b = maxSquare(x + 1, y, matrix, cache);
        int c = maxSquare(x, y + 1, matrix, cache);
        int result = 1 + Math.min(a, Math.min(b, c));
        cache.put(key(x, y), result);
        return result;

    }

    int key(int x, int y) {
        return x * 1000 + y;
    }
}
