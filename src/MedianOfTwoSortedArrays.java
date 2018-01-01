import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 * <p>
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * <p>
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 */

public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {

        Test.assertEquals(1.5, test(
                Collections.singletonList(1),
                Collections.singletonList(2)));
        Test.assertEquals(3.5, test(
                Arrays.asList(1, 3, 5),
                Arrays.asList(2, 4, 6)));
        Test.assertEquals(2.5, test(
                Arrays.asList(1, 3),
                Arrays.asList(2, 4)));
        int[] nums3 = {1, 3};
        int[] nums4 = {2, 4};
        Test.assertEquals(2.5, new Solution().findMedianSortedArrays(nums3, nums4));

        int[] nums7 = {1, 4};
        int[] nums8 = {3, 3};
        Test.assertEquals(3.0, new Solution().findMedianSortedArrays(nums7, nums8));

        int[] nums9 = {1, 3};
        int[] nums10 = {2};
        Test.assertEquals(2.0, new Solution().findMedianSortedArrays(nums9, nums10));

        Test.assertEquals(3, test(
                Arrays.asList(1, 2, 5),
                Arrays.asList(3, 4)));

        int[] nums13 = {1, 4};
        int[] nums14 = {2, 3};
        Test.assertEquals(2.5, new Solution().findMedianSortedArrays(nums13, nums14));

        int[] nums11 = {-1, 2, 3, 4};
        int[] nums12 = {1};
        Test.assertEquals(2.0, new Solution().findMedianSortedArrays(nums11, nums12));

        Test.assertEquals(3, test(
                Arrays.asList(1, 2, 7),
                Arrays.asList(3, 4)));


    }

    public static double test(List<Integer> a, List<Integer> b) {
        int[] array1 = new int[a.size()];
        for (int i = 0; i < a.size(); i++) {
            array1[i] = a.get(i);
        }
        int[] array2 = new int[b.size()];
        for (int i = 0; i < b.size(); i++) {
            array2[i] = b.get(i);
        }
        return new Solution().findMedianSortedArrays(array1, array2);
    }

    static class Solution {
        double findMedianSortedArrays(int[] nums1, int[] nums2) {

            // 1 2 3 5 6 6 8
            // 4 5 5 8 9 9 9 10 12

            int l1 = nums1.length;
            int l2 = nums2.length;

            int step = Math.min(l1, l2) / 2;

            int m1 = l1 / 2;
            int m2 = l2 / 2;

            // achieve symmetry
            if (l1 % 2 == 0 && l2 % 2 == 0) {
                m1--;
            }

            while (step > 0) {
                if (nums1[m1] > nums2[m2]) {
                    m1 -= step;
                    m2 += step;
                } else {
                    m1 += step;
                    m2 -= step;
                }
                step /= 2;
            }

            if (l1 % 2 == 0 && l2 % 2 == 0) {
                List<Integer> l = new ArrayList<>();
                l.add(nums1[m1]);
                l.add(nums2[m2]);
                if (nums1[m1] > nums2[m2]) {
                    l.add(nums1[m1 - 1]);
                    l.add(nums2[m2 + 1]);
                } else {
                    l.add(nums1[m1 + 1]);
                    l.add(nums2[m2 - 1]);
                }
                Collections.sort(l);
                return (l.get(1) + l.get(2)) / 2.0;
            } else if (l1 % 2 == 1 && l2 % 2 == 1) {
                return (nums1[m1] + nums2[m2]) / 2.0;
            } else if (l1 % 2 == 0) {
                List<Integer> l = new ArrayList<>();
                l.add(nums1[m1]);
                l.add(nums2[m2]);
                if (nums1[m1] > nums2[m2]) {
                    l.add(nums1[m1 - 1]);
                } else {
                    l.add(nums1[m1 + 1]);
                }
                Collections.sort(l);
                return l.get(1);
            } else {
                List<Integer> l = new ArrayList<>();
                l.add(nums1[m1]);
                l.add(nums2[m2]);
                if (nums1[m1] < nums2[m2]) {
                    l.add(nums2[m2 - 1]);
                } else {
                    l.add(nums2[m2 + 1]);
                }
                Collections.sort(l);
                return l.get(1);
            }
        }
    }
}