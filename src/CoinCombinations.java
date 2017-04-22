import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a set of different coins and an amount, print all the different combinations which sum up to the given amount
 */
public class CoinCombinations {

    /**
     * Test
     */
    public static void main(String[] args) {

        int[] coins = {5, 2, 1};
        int amount = 50;
        long t = System.currentTimeMillis();
        Set<Comb> c = combinations(coins, amount);
        System.out.println("took " + (System.currentTimeMillis() - t) + " ms, there are " + c.size() + " combinations");
        print(c);
    }

    static Set<Comb> combinations(int[] coins, int amount) {
        return combinations(coins, amount, new HashMap<>());
    }

    static Set<Comb> combinations(int[] coins, int residual, Map<Integer, Set<Comb>> lookup) {
        if (lookup.containsKey(residual)) {
            return lookup.get(residual);
        } else {
            Set<Comb> combs = new HashSet<>();
            for (int i = 0; i < coins.length; i++) {
                int r = residual - coins[i];
                if (r > 0) {
                    Set<Comb> subCombinations = combinations(coins, r, lookup);
                    for (Comb sc : subCombinations) {
                        int[] comb = sc.c.clone();
                        comb[i]++;
                        combs.add(new Comb(comb));
                    }
                } else if (r == 0) {
                    int[] comb = new int[coins.length];
                    comb[i]++;
                    combs.add(new Comb(comb));
                }
            }
            lookup.put(residual, combs);
            return combs;
        }
    }

    static class Comb implements Comparable<Comb> {

        Comb(int[] c) {
            this.c = c;
        }

        final int[] c;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Comb comb = (Comb) o;
            return Arrays.equals(c, comb.c);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(c);
        }

        @Override
        public int compareTo(Comb comb) {
            return this.hashCode() - comb.hashCode();
        }

        @Override
        public String toString() {
            return Arrays.toString(c);
        }
    }

    static void print(Set<Comb> c) {
        for (Comb comb : c) {
            System.out.println(comb);
        }
    }
}
