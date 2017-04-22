import java.math.BigInteger;
import java.util.Random;

/**
 * https://leetcode.com/problems/super-pow/#/description
 */
public class SuperPow {

    public static void main(String[] args) {

        // visualisation of the basic rule of modulo arithmetic: (a mod n)(b mod n) mod n = a*b mod n
        Random r = new Random();
        int max = 100;

        for (int i = 0; i < 10; i++) {
            int n = r.nextInt(max);
            int a = r.nextInt(max);
            int b = r.nextInt(max);
            System.out.println("a: " + a + " b: " + b + " n: " + n);
            System.out.println((((a % n) * (b % n)) % n) + " = " + ((a * b) % n));
        }

        int[] b = {3, 4, 5, 6, 7, 8, 9, 0};
        System.out.println(new SuperPow().superPow(1234, b));
    }

    public int superPow(int a, int[] b) {
        StringBuilder intVal = new StringBuilder();
        for (int digit : b) {
            intVal.append(digit);
        }
        BigInteger exponent = new BigInteger(intVal.toString());
        BigInteger base = new BigInteger(a + "");
        BigInteger mod = new BigInteger("1337");
        return base.modPow(exponent, mod).intValue();
    }

    /*
    public int superPow(int a, BigInteger exponent) {
        if(exponent.)
        BigInteger left = exponent.divide(new BigInteger("2"));
        BigInteger right = exponent.subtract(left);
    }*/

}
