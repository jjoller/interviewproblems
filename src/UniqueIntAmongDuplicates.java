/**
 * https://www.interviewcake.com/question/java/find-unique-int-among-duplicates?
 */
public class UniqueIntAmongDuplicates {

    public static void main(String[] args) {

        int[] ids = {4, 1, 4, 1, 3, 6, 6};
        Test.assertEquals(unique(ids), 3);
        System.out.println("Test succeeded");
    }

    static int unique(int[] ids) {
        int unique = 0;
        for (int i : ids)
            unique ^= i;
        return unique;
    }
}
