import java.util.Random;

/**
 * This problem was asked by Amazon.
 * <p>
 * Given an array of numbers, find the maximum sum of any contiguous subarray of the array.
 * <p>
 * For example, given the array [34, -50, 42, 14, -5, 86], the maximum sum would be 137, since we would take elements 42, 14, -5, and 86.
 * <p>
 * Given the array [-5, -1, -8, -9], the maximum sum would be 0, since we would not take any elements.
 */
public class MaximumSubarraySum {

    public static void main(String[] args) {

        int[] a1 = {34, -50, 42, 14, -5, 86};
        Test.assertEquals(137, maxSubarraySum(a1));

        int[] a2 = {-5, -1, -8, -9};
        Test.assertEquals(0, maxSubarraySum(a2));

        // Test on random arrays
        Random r = new Random();
        for (int i = 0; i < 1000; i++) {
            int[] a = randomArray(10, r);
            Test.assertEquals(maxSubarraySumBruteForce(a), maxSubarraySum(a));
        }
    }

    static int maxSubarraySum(int[] a) {
        int max = 0;
        int sum = 0;
        for (int e : a) {
            sum = Math.max(0, sum + e);
            max = Math.max(sum, max);
        }
        return max;
    }

    /**
     * Brute force version for checking whether the O(n) version returns the correct answer.
     */
    static int maxSubarraySumBruteForce(int[] a) {
        int max = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += a[k];
                }
                max = Math.max(sum, max);
            }
        }
        return max;
    }

    /**
     * Generating test inputs.
     *
     * @param n Size of the array
     * @param r Random number generator
     */
    static int[] randomArray(int n, Random r) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(1000) - 500;
        }
        return a;
    }

}
