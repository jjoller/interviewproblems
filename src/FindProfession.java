/**
 * https://codefights.com/interview-practice/task/FwAR7koSB3uYYsqDp
 * <p>
 * Consider a special family of Engineers and Doctors. This family has the following rules:
 * <p>
 * Everybody has two children.
 * The first child of an Engineer is an Engineer and the second child is a Doctor.
 * The first child of a Doctor is a Doctor and the second child is an Engineer.
 * All generations of Doctors and Engineers start with an Engineer.
 * We can represent the situation using this diagram:
 * <p>
 * E
 * /         \
 * E           D
 * /   \        /  \
 * E     D      D    E
 * / \   / \    / \   / \
 * E   D D   E  D   E E   D
 * Given the level and position of a person in the ancestor tree above, find the profession of the person.
 * Note: in this tree first child is considered as left child, second - as right.
 * <p>
 * Example
 * <p>
 * For level = 3 and pos = 3, the output should be
 * findProfession(level, pos) = "Doctor".
 * <p>
 * Input/Output
 * <p>
 * [execution time limit] 3 seconds (java)
 * <p>
 * [input] integer level
 * <p>
 * The level of a person in the ancestor tree, 1-based.
 * <p>
 * Guaranteed constraints:
 * 1 ≤ level ≤ 30.
 * <p>
 * [input] integer pos
 * <p>
 * The position of a person in the given level of ancestor tree, 1-based, counting from left to right.
 * <p>
 * Guaranteed constraints:
 * 1 ≤ pos ≤ 2(level - 1).
 * <p>
 * [output] string
 * <p>
 * Return Engineer or Doctor.
 */
public class FindProfession {

    public static void main(String[] args) {
        FindProfession find = new FindProfession();
        Test.assertEquals(find.d, find.findProfession(3, 3));
        Test.assertEquals(find.d, find.findProfession(4, 2));
        Test.assertEquals(find.e, find.findProfession(1, 1));
        Test.assertEquals(find.d, find.findProfession(2, 2));
        Test.assertEquals(find.d, find.findProfession(3, 3));
        Test.assertEquals(find.e, find.findProfession(3, 4));
        Test.assertEquals(find.e, find.findProfession(10, 470));
        System.out.println("Tests successful");
    }

    String findProfession(int level, int pos) {
        return find(e, level, pos);
    }

    String e = "Engineer";
    String d = "Doctor";

    String find(String parent, int level, int pos) {
        if (level == 1) {
            if (parent.equals(e)) {
                return pos == 1 ? e : d;
            } else {
                return pos == 1 ? d : e;
            }
        }
        int half = 1 << (level - 2);
        if (parent.equals(e)) {
            return pos > half ? find(d, level - 1, pos - half) : find(e, level - 1, pos);
        } else {
            return pos > half ? find(e, level - 1, pos - half) : find(d, level - 1, pos);
        }
    }
}
