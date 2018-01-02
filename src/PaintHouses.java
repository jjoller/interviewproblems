/**
 * https://codefights.com/interview-practice/task/idSmSy6u2LNiNDjKw
 * <p>
 * In San Francisco, there is a row of several beautiful houses called the Painted Ladies. Each of the Painted Ladies can be painted with one of three colors: red, blue or green. The cost of painting each house with a certain color is different. cost[i][0] for each i is the cost of painting house i red, cost[i][1] is the cost of painting it blue, and cost[i][2] is the cost of painting it green.
 * <p>
 * You want to paint all the houses in a way such that no two adjacent Painted Ladies have the same color. Find the minimum cost to achieve this.
 * <p>
 * Example
 * <p>
 * For cost = [[1, 3, 4], [2, 3, 3], [3, 1, 4]], the output should be
 * paintHouses(cost) = 5.
 * <p>
 * You can paint the first Painted Lady red for a cost of 1, the second one green for a cost of 3, and the third one blue for a cost of 1, for a total cost of 5.
 * <p>
 * Input/Output
 * <p>
 * [execution time limit] 3 seconds (java)
 * <p>
 * [input] array.array.integer cost
 * <p>
 * The costs of painting each Painted Lady a certain color following the guidelines: cost[i][0] for each i is the cost of painting house i red, cost[i][1] is the cost of painting it blue, and cost[i][2] is the cost of painting it green.
 * <p>
 * Guaranteed constraints:
 * 1 ≤ cost.length ≤ 105,
 * cost[i].length = 3,
 * 1 ≤ cost[i][j] ≤ 104.
 * <p>
 * [output] integer
 * <p>
 * The minimal cost of painting all the Painted Ladies so that no two adjacent houses are the same color.
 * <p>
 */
public class PaintHouses {

    public static void main(String[] args) {
        int[][] cost = {{1, 1, 1}};
        Test.assertEquals(1, new PaintHouses().paintHouses(cost));
        System.out.println("Tests succeeded");
    }

    int paintHouses(int[][] cost) {
        int n = 3;
        int[][] cache = new int[2][n];
        int z = 0;
        for (int c = 0; c < n; c++) {
            cache[z][c] = cost[0][c];
        }
        int min;
        for (int i = 1; i < cost.length; i++) {
            for (int c = 0; c < n; c++) {
                min = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    if (c != k) {
                        min = Math.min(min, cost[i][c] + cache[z][k]);
                    }
                }
                cache[(z + 1) % 2][c] = min;
            }
            z = (z + 1) % 2;
        }
        min = Integer.MAX_VALUE;
        for (int c = 0; c < n; c++) {
            min = Math.min(min, cache[z][c]);
        }
        return min;
    }
}
