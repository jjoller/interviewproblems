import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Given two permutations, a target and a transformation. Calculate if a sorted permutation (0,1,2,...,n) can be
 * transformed into the target permutation by applying the transformation multiple times.
 *
 * TODO is there a more efficient way?
 *
 */
public class Permutations {

    /**
     * Test
     */
    public static void main(String[] args) {

        int n = 5;
        Random random = new Random();

        Permutation transformation = new Permutation(n, random);
        Set<Permutation> permutations = allPermutations(new Permutation(n), transformation);

        for (int i = 0; i < 100; i++) {
            Permutation target = new Permutation(n, random);
            if (permutations.contains(target)) {
                System.out.println(target + " can be reached by " + transformation);
            }else{
                System.out.println(target + " can NOT be reached by " + transformation);
            }
        }
    }

    /**
     * Calculate all the permutations that are possible by applying a given transformation to a given starting
     * permutation
     */
    static Set<Permutation> allPermutations(Permutation target, Permutation transformation) {

        Set<Permutation> permutations = new HashSet<>();
        while (!permutations.contains(target)) {
            permutations.add(target);
            target = target.apply(transformation);
        }
        return permutations;
    }

    static class Permutation implements Comparable<Permutation> {

        Permutation(int[] p) {
            this.p = p;
        }

        /**
         * A sorted "permutation" (0,1,2,...,n)
         */
        Permutation(int n) {
            p = new int[n];
            for (int i = 0; i < n; i++)
                p[i] = i;
        }

        /**
         * Random permutation of a certain length
         */
        Permutation(int n, Random random) {
            p = new int[n];
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++)
                list.add(i);
            Collections.shuffle(list, random);
            for (int i = 0; i < n; i++)
                p[i] = list.get(i);
        }

        final int[] p;

        Permutation apply(Permutation p) {
            if (p.p.length != this.p.length) {
                throw new IllegalArgumentException();
            }
            int n = p.p.length;
            int[] newP = new int[n];
            for (int i = 0; i < n; i++) {
                newP[i] = this.p[p.p[i]];
            }
            return new Permutation(newP);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Permutation that = (Permutation) o;

            return Arrays.equals(p, that.p);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(p);
        }

        @Override
        public int compareTo(Permutation permutation) {
            return this.hashCode() - permutation.hashCode();
        }

        @Override
        public String toString() {
            return "Permutation{" +
                    "p=" + Arrays.toString(p) +
                    '}';
        }
    }
}