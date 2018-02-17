import java.util.Random;

/**
 * Using a function rand5() that returns an integer from 1 to 5 (inclusive) with uniform probability, implement a
 * function rand7() that returns an integer from 1 to 7 (inclusive).
 */
public class Random7from5 {

    public static void main(String[] args) {
        Random7from5 random = new Random7from5();
        for (int i = 0; i < 1000; i++) {
            int r = random.rand7();
            Test.assertTrue(r <= 7);
            Test.assertTrue(r > 0);
        }
    }

    private Random random = new Random();

    int rand7() {
        int r = 0;
        while (r == 0) {
            for (int i = 0; i < 3; i++) {
                r |= randomBit() << i;
            }
        }
        return r;
    }

    int randomBit() {
        int r = 5;
        while (r == 5) {
            r = rand5();
        }
        if (r < 3) {
            return 0;
        } else {
            return 1;
        }
    }

    int rand5() {
        return random.nextInt(5) + 1;
    }

}
