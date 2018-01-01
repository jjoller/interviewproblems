import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Write a method to randomly generate a set of m integers from an array of length n.
 * Each element must have equal probability of being chosen.
 */
public class RandomSet {

    public static void main(String[] args) {

    }

    public Set<Integer> randomlyChooseM(int[] array, int m) {

        // O (n * m)

        Set<Integer> result = new HashSet<>();
        Random random = new Random();

        int n = array.length;
        int k = random.nextInt(n);
        result.add(array[k]);

        while (result.size() < m) {

            for (int i = 0; i < n; i++) {
                if (random.nextDouble() < m / n - i) {
                    result.add(array[i]);
                }
            }
        }

        return result;
    }

}
