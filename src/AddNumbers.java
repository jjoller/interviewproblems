/**
 * Write a function that adds two numbers. You should not use + or any arithmetic operators.
 */
public class AddNumbers {

    public static void main(String[] args) {
        Test.assertEquals(2, add(1, 1));
        Test.assertEquals(9, add(5, 4));
        Test.assertEquals(17, add(8, 9));
        Test.assertEquals(135 + 12345, add(135, 12345));
    }

    public static int add(int a, int b) {
        int result = 0;
        int z = 0;
        int mask = 1;
        while (mask > 0) {
            int bitA = a & mask;
            int bitB = b & mask;
            int bit = (bitA ^ bitB ^ z) | (bitA & bitB & z);
            z = bitA & bitB | bitA & z | bitB & z;
            z = z << 1;
            result = result | bit;
            mask = mask << 1;
        }
        return result;
    }

}
