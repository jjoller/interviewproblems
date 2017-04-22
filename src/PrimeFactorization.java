import java.util.ArrayList;
import java.util.List;

/**
 * given an integer n, calculate its prime factorization
 */
public class PrimeFactorization {

    public static void main(String[] args) {
        System.out.println(factorization(Integer.MAX_VALUE - 1));
    }

    static List<Integer> factorization(int n) {
        List<Integer> factors = new ArrayList<>();
        int q = (int) Math.sqrt(n);
        for (int i = q; i > 1; i -= 2) {
            if (n % i == 0) {
                factors.addAll(factorization(i));
                factors.addAll(factorization(n / i));
                return factors;
            }
        }
        factors.add(n);
        return factors;
    }

}
