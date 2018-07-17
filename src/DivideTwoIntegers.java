import java.util.Random;

/**
 * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
 * <p>
 * Return the quotient after dividing dividend by divisor.
 * <p>
 * The integer division should truncate toward zero.
 */
public class DivideTwoIntegers {

    public static void main(String[] args) {
        Random random = new Random();
        DivideTwoIntegers d = new DivideTwoIntegers();
        for (int i = 0; i < 1000; i++) {
            int dividend = random.nextInt(100000);
            int divisor = random.nextInt(dividend);
            Test.assertEquals(dividend / divisor, d.divide(dividend, divisor));
        }
    }

    public int divide(int dividend, int divisor) {
        long dividendLong = dividend;
        long divisorLong = divisor;
        boolean negation;
        if (dividendLong < 0 && divisorLong > 0) {
            negation = true;
            dividendLong = -dividendLong;
        } else if (dividendLong > 0 && divisorLong < 0) {
            negation = true;
            divisorLong = -divisorLong;
        } else if (dividendLong < 0 && divisorLong < 0) {
            dividendLong = -dividendLong;
            divisorLong = -divisorLong;
            negation = false;
        } else {
            negation = false;
        }

        long quotient = 0;
        long remaining = dividendLong;
        for (int i = 32; i >= 0; i--) {
            long shifted = divisorLong << i;
            if (shifted > 0) {
                if (shifted <= remaining) {
                    quotient |= 1 << i;
                    remaining -= shifted;
                }
            }
        }
        if (negation) {
            return (int) -quotient;
        } else {
            return (int) quotient;
        }
    }

}
