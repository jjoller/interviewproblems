import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Given a set of cards and a permutation
 */
public class Permutations {

    public static void main(String[] args) {
        int n = 52;
        Random random = new Random();

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 100000; i++) {
            Permutation start = new Permutation(n);
            Permutation permutation = new Permutation(n, random);
            int s = allPermutations(start, permutation).size();
            if (s > max) {
                System.out.println(s);
                max = s;
            }
        }
        System.out.println(max);
    }

    public static Set<Permutation> allPermutations(Permutation start, Permutation permutation) {

        Set<Permutation> permutations = new HashSet<>();
        while (!permutations.contains(start)) {
            permutations.add(start);
            start = start.apply(permutation);
        }
        return permutations;
    }

    static class Permutation implements Comparable<Permutation> {

        public Permutation(int[] p) {
            this.p = p;
        }

        public Permutation(int n) {
            p = new int[n];
            for (int i = 0; i < n; i++)
                p[i] = i;
        }

        public Permutation(int n, Random random) {
            p = new int[n];
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++)
                list.add(i);
            Collections.shuffle(list, random);
            for (int i = 0; i < n; i++)
                p[i] = list.get(i);
        }

        final int[] p;

        public Permutation apply(Permutation p) {
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