import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Cyclically sorted array (Elements of Programming Interviews in Java page 177)
 * <p>
 * Design an O(log n) algorithm for finding the position of the smallest element in a cyclically sorted array.Assume all
 * elements are distinct.
 */
public class CyclicallySortedArray {

    /**
     * Tests
     */
    public static void main(String[] args) {

        int[] a1 = {8, 9, 12, 3, 4, 4, 6, 7};
        Test.assertEquals(searchSmallest(a1), 3);

        int[] a2 = {3, 7, 1};
        Test.assertEquals(searchSmallest(a2), 1);

        int[] a3 = {7, 8, 9, 1, 2, 3, 4, 5, 6, 7};
        Test.assertEquals(searchSmallest(a3), 1);
    }

    static int searchSmallest(int[] a) {
        List<Integer> A = new ArrayList<>();
        Arrays.stream(a).forEach(A::add);
        int left = 0, right = A.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (A.get(mid) > A.get(right)) {
                if (mid == left) {
                    return a[right];
                } else {
                    left = mid;
                }
            } else {
                right = mid;
            }
        }
        return a[left];
    }
}