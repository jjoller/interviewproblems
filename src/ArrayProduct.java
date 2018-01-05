/**
 * Given an array of integers, return a new array such that each element at index i of the new array is the product of
 * all the numbers in the original array except the one at i. Solve it without using division and in O(n) time.
 * <p>
 * For example, if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24].
 * If our input was [3, 2, 1], the expected output would be [2, 3, 6].
 */
public class ArrayProduct {

    public static void main(String[] args) {
        int[] a1 = {3, 2, 1};
        int[] p1 = {2, 3, 6};
        Test.assertEquals(p1, product(a1));

        int[] a2 = {1, 2, 3, 4, 5};
        int[] p2 = {120, 60, 40, 30, 24};
        Test.assertEquals(p2, product(a2));

        int[] a3 = {-1, 2, 3, 4, 5};
        int[] p3 = {120, -60, -40, -30, -24};
        Test.assertEquals(p3, product(a3));

        System.out.println("Tests succeeded");
    }

    static int[] product(int[] a) {
        int n = a.length;
        int[] p = new int[n];
        int[] leftProducts = new int[n];
        int[] rightProducts = new int[n];
        leftProducts[0] = a[0];
        for (int i = 1; i < n; i++) {
            leftProducts[i] = leftProducts[i - 1] * a[i];
        }
        rightProducts[n - 1] = a[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightProducts[i] = rightProducts[i + 1] * a[i];
        }
        p[0] = rightProducts[1];
        p[n - 1] = leftProducts[n - 2];
        for (int i = 1; i < n - 1; i++) {
            p[i] = leftProducts[i - 1] * rightProducts[i + 1];
        }
        return p;
    }
}