import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 * <p>
 * Longest Substring Without Repeating Characters
 * <p>
 * <p>
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Examples:
 * <p>
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * <p>
 * Given "bbbbb", the answer is "b", with the length of 1.
 * <p>
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a
 * subsequence and not a substring.
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        Test.assertEquals(0, new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring(""));
        Test.assertEquals(3, new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("abcabcbb"));
        Test.assertEquals(1, new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("bbbbb"));
    }

    public int lengthOfLongestSubstring(String s) {
        LinkedHashSet<Character> subset = new LinkedHashSet<>();
        LinkedList<Character> subseq = new LinkedList<>();
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            while (subset.contains(c)) {
                subset.remove(subseq.removeFirst());
            }
            subseq.addLast(c);
            subset.add(c);
            max = Math.max(max, subseq.size());
        }
        return max;
    }
}