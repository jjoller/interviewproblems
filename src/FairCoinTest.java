import java.math.BigInteger;

/**
 * A coin was flipped 1000 times and there where 560 heads. Do you think the coin is fair?
 */
public class FairCoinTest {

    public static void main(String[] args) {

        BigInteger a = new BigInteger("0");
        BigInteger b = new BigInteger("0");

        int n = 1000;
        for (int i = 0; i < 559; i++) {
            a = a.add(binomial(n, i));
        }
        for (int i = 560; i <= n; i++) {
            b = b.add(binomial(n, i));
        }

        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println(b.divide(a.add(b)));

    }

    static BigInteger binomial(final int N, final int K) {
        BigInteger ret = BigInteger.ONE;
        for (int k = 0; k < K; k++) {
            ret = ret.multiply(BigInteger.valueOf(N - k))
                    .divide(BigInteger.valueOf(k + 1));
        }
        return ret;
    }
}
