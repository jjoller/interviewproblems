import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Cyclically sorted array (EPI p. 177)
 */
public class CyclicallySortedArray {

    public static void main(String[] args) {

        //int[] a = {8, 9, 12, 3, 4, 4, 6, 7};

        //int[] a = {4, 4, 6, 7, 1};

//        int[] a = {7, 8, 9, 1, 2, 3, 4, 5, 6, 7};

        int[] a = {0,0,0,0,0,0,0,0,0,0,0,0,0,-1,0,0,0};



        System.out.println("min: " + searchSmallest(a));
        System.out.println("min: " + findMin(a));
    }

    static int searchSmallest(int[] a) {
        List<Integer> A = new ArrayList<>();
        Arrays.stream(a).forEach(A::add);
        int left = 0, right = A.size() - 1;
        while (left < right) {
            System.out.println("l: " + left + " r: " + right);
            int mid = left + ((right - left) / 2);
            if (A.get(mid) > A.get(right)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return a[left];
    }

    static int findMin(int[] a) {
        return min(a, 0, a.length - 1);
    }

    static int min(int[] a, int l, int r) {
        // System.out.println("l: " + l + " r: " + r);

        if (l + 1 == r) {
            return a[l] < a[r] ? a[l] : a[r];
        } else if (l < r) {
            int m = l + (r - l) / 2;
            if (a[l] > a[m]) {
                return min(a, l, m);
            } else if (a[m] > a[r]) {
                return min(a, m, r);
            } else {
                return min(a, l, m);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }


}
