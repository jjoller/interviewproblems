/**
 * https://codefights.com/interview-practice/task/Sx8ndFtwEyCRRqF7q
 * <p>
 * Implement regular expression matching with support for '.' and '*', given the following guidelines:
 * '.' Matches any single character.
 * '*' Matches zero or more of the element that comes before it.
 * <p>
 * The matching should cover the entire input string s. If the pattern p matches the input string s, return true, otherwise return false.
 * <p>
 * Example
 * <p>
 * For s = "bb" and p = "b", the output should be
 * regularExpressionMatching(s, p) = false;
 * For s = "zab" and p = "z.*", the output should be
 * regularExpressionMatching(s, p) = true;
 * For s = "caab" and p = "d*c*x*a*b", the output should be
 * regularExpressionMatching(s, p) = true.
 * Input/Output
 * <p>
 * [execution time limit] 3 seconds (java)
 * <p>
 * [input] string s
 * <p>
 * A string consisting of only lowercase English letters.
 * <p>
 * Guaranteed constraints:
 * 0 ≤ s.length ≤ 20.
 * <p>
 * [input] string p
 * <p>
 * A string consisting of only lowercase English letters and the characters . and *.
 * <p>
 * Guaranteed constraints:
 * 0 ≤ p.length ≤ 30.
 * <p>
 * [output] boolean
 * <p>
 * Return true if the pattern p matches the string s given the guidelines above, otherwise return false.
 */
public class RegularExpressionMatching {

    public static void main(String[] args) {
        RegularExpressionMatching matching = new RegularExpressionMatching();
        Test.assertEquals(false, matching.regularExpressionMatching("bb", "b"));
        Test.assertEquals(true, matching.regularExpressionMatching("zab", "z.*"));
        Test.assertEquals(true, matching.regularExpressionMatching("caab", "d*c*x*a*b"));
        Test.assertEquals(false, matching.regularExpressionMatching("aaa", "aaaz"));
    }

    boolean regularExpressionMatching(String s, String p) {
        if (s.length() == 0 && p.length() == 0) {
            return true;
        }
        if (p.length() > 0) {
            if (s.length() > 0 && (p.startsWith(".") || p.charAt(0) == s.charAt(0))) {
                if (p.length() > 1 && p.charAt(1) == '*') {
                    if (regularExpressionMatching(s.substring(1), p)) {
                        return true;
                    }
                }
                if (regularExpressionMatching(s.substring(1), p.substring(1))) {
                    return true;
                }
            }
            if (p.length() > 1 && p.charAt(1) == '*') {
                return regularExpressionMatching(s, p.substring(2));
            }
        }
        return false;
    }
}
