/**
 * https://leetcode.com/problems/powx-n/
 * <p>
 * Implement pow(x, n), which calculates x raised to the power n (x^n).
 */
public class PowXN {

    public static void main(String[] args) {
        PowXN powXN = new PowXN();
        Test.assertEquals(Math.pow(1, 2), powXN.myPow(1, 2));
        Test.assertEquals(Math.pow(2, 2), powXN.myPow(2, 2));
        Test.assertEquals(Math.pow(0.5, -2), powXN.myPow(0.5, -2));
        Test.assertEquals(Math.pow(0.5, 0), powXN.myPow(0.5, 0));
        Test.assertEquals(Math.pow(2.00000, -2147483648), powXN.myPow(2.00000, -2147483648));
    }

    public double myPow(double x, int n) {
        if (x != 1 && x != -1 && n == Integer.MIN_VALUE) {
            return 0.0;
        }
        int e = n < 0 ? -n : n;
        double result = 1.0;
        while (e > 0) {
            if ((e % 2) == 1) {
                result *= x;
            }
            e >>= 1;
            x *= x;
        }
        return n < 0 ? (1 / result) : result;
    }

}
