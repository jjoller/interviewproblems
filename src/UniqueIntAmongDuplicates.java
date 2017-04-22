/**
 * https://www.interviewcake.com/question/java/find-unique-int-among-duplicates?
 */
public class UniqueIntAmongDuplicates {

    public static void main(String[] args) {

        int[] ids = {4, 1, 4, 1, 3, 6, 6};

        assertEquals(unique(ids), 3);
    }

    static void assertEquals(Object a, Object b) {
        if (!a.equals(b)) {
            throw new IllegalArgumentException("Test failed " + a + " does not equal " + b);
        }
    }

    static int unique(int[] ids) {
        int unique = 0;
        for (int i : ids)
            unique ^= i;
        return unique;
    }
}
