import java.util.*;

/**
 * Given an array of integers, return the smallest positive integer that is missing.
 */
public class SmallestMissingPositive {

    public static void main(String[] args) {
        // run a few randomized tests
        Random random = new Random(1234);
        for (int i = 0; i < 1000; i++) {
            long seed = random.nextLong();
            int size = 100;
            int smallest = smallestMissing(randomArray(size, seed));
            Test.assertEquals(smallest, smallestMissingBruteForce(randomArray(size, seed)));
        }
        System.out.println("Tests succeeded");
    }

    static int smallestMissing(int[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i]--;
        }
        for (int i = 0; i < a.length; i++) {
            int temp = a[i];
            if (i != temp) {
                a[i] = -1;
                mark(temp, a);
            }
        }
        int i = 0;
        for (; i < a.length; i++) {
            if (a[i] == -1) {
                return i + 1;
            }
        }
        return i + 1;
    }

    static void mark(int i, int[] a) {
        if (i >= 0 && i < a.length) {
            int temp = a[i];
            if (temp != i) {
                a[i] = i;
                mark(temp, a);
            }
        }
    }

    static int smallestMissingBruteForce(int[] a) {
        Set<Integer> l = new HashSet<>();
        for (int e : a) {
            l.add(e);
        }
        int i = 1;
        for (; i < a.length; i++) {
            if (!l.contains(i)) {
                return i;
            }
        }
        return i;
    }

    static int[] randomArray(int size, long seed) {
        int[] a = new int[size];
        Random r = new Random(seed);
        for (int i = 0; i < size; i++) {
            a[i] = i;
        }
        for (int i = 0; i < size / 2; i++) {
            a[r.nextInt(size)] = r.nextInt();
        }
        return a;
    }
}