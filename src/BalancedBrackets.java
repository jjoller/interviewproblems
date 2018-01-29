/**
 * This problem was asked by Facebook.
 * <p>
 * Given a string of round, curly, and square open and closing brackets, return whether the brackets are balanced (well-formed).
 * <p>
 * For example, given the string "([])[]({})", you should return true.
 * <p>
 * Given the string "([)]" or "((()", you should return false.
 */
public class BalancedBrackets {

    public static void main(String[] args) {
        BalancedBrackets b = new BalancedBrackets();
        Test.assertEquals(true, b.balanced("()"));
        Test.assertEquals(false, b.balanced(")("));
        Test.assertEquals(false, b.balanced(")"));
        Test.assertEquals(true, b.balanced("[()]"));
        Test.assertEquals(true, b.balanced("[()]()"));
        Test.assertEquals(true, b.balanced("([])[]({})"));
        Test.assertEquals(false, b.balanced("([)]"));
        Test.assertEquals(false, b.balanced("((()"));
    }

    boolean balanced(String v) {
        return "".equals(matchGroup(v));
    }

    String matchGroup(String v) {
        if (v.length() < 1) {
            return "";
        }
        char b = v.charAt(0);
        if (isOpeningBracket(b)) {
            String tail = matchGroup(v.substring(1));
            if (tail != null && tail.length() > 0 && isMatching(b, tail.charAt(0))) {
                return matchGroup(tail.substring(1));
            } else {
                return null;
            }
        } else if (isClosingBracket(b)) {
            return v;
        } else {
            return null;
        }
    }

    boolean isOpeningBracket(char b) {
        return b == '(' || b == '[' || b == '{';
    }

    boolean isClosingBracket(char b) {
        return b == ')' || b == ']' || b == '}';
    }

    boolean isMatching(char opening, char closing) {
        return opening == '(' && closing == ')' || opening == '[' && closing == ']' || opening == '{' && closing == '}';
    }

}
