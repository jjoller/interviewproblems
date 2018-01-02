/**
 * https://codefights.com/interview-practice/task/x3rJpdZGEcjmYtDqv
 * <p>
 * A string is a k-palindrome if it can be transformed into a palindrome by removing any amount of characters from 0 to k. Your task is to determine whether the given string s is a k-palindrome.
 * <p>
 * Example
 * <p>
 * For s = "abrarbra" and k = 1, the output should be
 * kpalindrome(s, k) = true.
 * <p>
 * You can remove one letter, 'r', to obtain the string "abrarba", which is a palindrome.
 * <p>
 * For s = "adbcdbacdb" and k = 2, the output should be
 * kpalindrome(s, k) = false.
 * <p>
 * Input/Output
 * <p>
 * [execution time limit] 3 seconds (java)
 * <p>
 * [input] string s
 * <p>
 * A string containing only lowercase English letters.
 * <p>
 * Guaranteed constraints:
 * 3 ≤ s.length ≤ 100.
 * <p>
 * [input] integer k
 * <p>
 * Guaranteed constraints:
 * 1 ≤ k ≤ 20.
 * <p>
 * [output] boolean
 * <p>
 * Return true if it is possible to delete at most k letters from a string s in order to make it palindrome. Return false otherwise.
 */
public class KPalindrome {

    public static void main(String[] args) {

        KPalindrome p = new KPalindrome();
        Test.assertEquals(true, p.kpalindrome("abrarbra", 1));
        Test.assertEquals(false, p.kpalindrome("adbcdbacdb", 2));
        System.out.println("Tests succeeded");
    }

    boolean kpalindrome(String s, int k) {
        if (s.length() < 2) {
            return true;
        }
        if (s.charAt(0) == s.charAt(s.length() - 1)) {
            return kpalindrome(s.substring(1, s.length() - 1), k);
        } else if (k > 0) {
            return kpalindrome(s.substring(1), k - 1) || kpalindrome(s.substring(0, s.length() - 1), k - 1);
        }
        return false;
    }
}
