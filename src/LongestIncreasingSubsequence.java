import java.util.HashMap;
import java.util.Map;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        LongestIncreasingSubsequence solution = new LongestIncreasingSubsequence();
        int[] s = {1, 231, 2, 4, 89, 32, 12, 234, 33, 90, 34, 100};
        Test.assertEquals(7, solution.longestIncreasingSubsequence(s));
        System.out.println("Tests succeeded");
    }

    int longestIncreasingSubsequence(int[] sequence) {
        return subSeq(0, Integer.MIN_VALUE, sequence, new HashMap<>());
    }

    int subSeq(int i, int max, int[] seq, Map<String, Integer> cache) {
        if (i >= seq.length) {
            return 0;
        }
        if (cache.containsKey(key(i, max))) {
            return cache.get(key(i, max));
        }
        int r;
        if (seq[i] > max) {
            r = Math.max(1 + subSeq(i + 1, seq[i], seq, cache), subSeq(i + 1, max, seq, cache));
        } else {
            r = subSeq(i + 1, max, seq, cache);
        }
        cache.put(key(i, max), r);
        return r;
    }

    String key(int i, int max) {
        return i + "#" + max;
    }
}