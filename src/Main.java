import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String[] wordList = {"abc", "abd", "cbc", "aec", "abd", "aed", "ced"};

        System.out.println(new Main().wordLadder("abc", "ced", wordList));

        String[] wordList2 = {"hot",
                "dot",
                "dog",
                "cog"};
        System.out.println(new Main().wordLadder("hit", "cog", wordList2));

        String[] wordList3 = {"hot",
                "dot",
                "dog",
                "lot",
                "log",
                "cog", "hit"};

        System.out.println(new Main().wordLadder("hit", "hit", wordList3));
        System.out.println(new Main().wordLadder("hot", "hit", wordList3));

        String[] wordList4 = {"hot",
                "dog",
                "cog",
                "pot",
                "dot"};
        System.out.println(new Main().wordLadder("hot", "dog", wordList4));
    }

    int wordLadder(String beginWord, String endWord, String[] wordList) {

        LinkedList<String> unvisited = new LinkedList<>();
        unvisited.addAll(Arrays.asList(wordList));
        Map<String, Integer> lookup = new HashMap<>();
        int pathLength = findPath(unvisited, beginWord, endWord, lookup);
        return pathLength <= wordList.length ? pathLength + 1 : 0;
    }

    int findPath(LinkedList<String> unvisited, String beginWord, String endWord, Map<String, Integer> lookup) {

        if (endWord.equals(beginWord)) {
            return 0;
        }
        if (unvisited.size() == 0) {
            return Short.MAX_VALUE;
        }
        String key = String.join(",", unvisited) + "," + beginWord;
        if (lookup.containsKey(key)) {
            return lookup.get(key);
        }
        int shortest = Short.MAX_VALUE;
        for (int i = 0; i < unvisited.size(); i++) {
            String next = unvisited.get(i);
            if (isDist1(beginWord, next)) {
                unvisited.remove(i);
                int pathLength = 1 + findPath(unvisited, next, endWord, lookup);
                unvisited.add(i, next);
                if (pathLength < shortest) {
                    shortest = pathLength;
                    if (shortest == 1) {
                        return 1;
                    }
                }
            }
        }
        lookup.put(key, shortest);
        return shortest;
    }

    boolean isDist1(String a, String b) {
        int diff = 0;
        int n = a.length();
        for (int i = 0; i < n; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
                if (diff > 1) {
                    return false;
                }
            }
        }
        return diff == 1;
    }
}
