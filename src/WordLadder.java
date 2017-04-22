import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class WordLadder {

    public static void main(String[] args) {
        String[] wordList = {"abc", "abd", "cbc", "aec", "abd", "aed", "ced"};

        System.out.println(new WordLadder().wordLadder("abc", "ced", wordList));

        String[] wordList2 = {"hot",
                "dot",
                "dog",
                "cog"};
        System.out.println(new WordLadder().wordLadder("hit", "cog", wordList2));

        String[] wordList3 = {"hot",
                "dot",
                "dog",
                "lot",
                "log",
                "cog", "hit"};

        System.out.println(new WordLadder().wordLadder("hit", "hit", wordList3));
        System.out.println(new WordLadder().wordLadder("hot", "hit", wordList3));

        String[] wordList4 = {"hot",
                "dog",
                "cog",
                "pot",
                "dot"};
        System.out.println(new WordLadder().wordLadder("hot", "dog", wordList4));
    }

    int wordLadder(String beginWord, String endWord, String[] wordList) {

        Map<String, Set<String>> neighbors = new HashMap<>();
        Set<String> words = new HashSet<>();
        words.addAll(Arrays.asList(wordList));
        words.add(beginWord);
        words.add(endWord);
        Iterator<String> iter1 = words.iterator();
        while (iter1.hasNext()) {
            String w1 = iter1.next();
            Iterator<String> iter2 = words.iterator();
            while (iter2.hasNext()) {
                String w2 = iter2.next();
                if (isDist1(w1, w2)) {
                    neighbors.putIfAbsent(w1, new HashSet<>());
                    neighbors.get(w1).add(w2);
                }
            }
        }

        Set<String> visitedSet = new HashSet<>();
        Map<String, Integer> distanceMap = new TreeMap<>();

        distanceMap.put(beginWord, 0);
        String node = lowestDistNode(distanceMap, visitedSet);
        while (node != null) {
            System.out.println("lowest dist node: " + node);
            // update neighbor nodes
            for (String neighbor : neighbors.get(node)) {
                int distance2Node = distanceMap.get(node);
                int distance2Neighbor = distanceMap.computeIfAbsent(neighbor, k -> Integer.MAX_VALUE);

                if (distance2Neighbor + 1 < distance2Node) {
                    distanceMap.put(node, distance2Neighbor + 1);
                } else if (distance2Node + 1 < distance2Neighbor) {
                    distanceMap.put(neighbor, distance2Node + 1);
                }
            }
            visitedSet.add(node);
            node = lowestDistNode(distanceMap, visitedSet);
        }
        return distanceMap.computeIfAbsent(endWord, k -> 0);
    }

    String lowestDistNode(Map<String, Integer> distanceMap, Set<String> visitedSet) {
        int lowestDist = Integer.MAX_VALUE;
        String lowestNode = null;
        for (Entry<String, Integer> e : distanceMap.entrySet()) {
            if (!visitedSet.contains(e.getKey())) {
                if (e.getValue() < lowestDist) {
                    lowestDist = e.getValue();
                    lowestNode = e.getKey();
                }
            }
        }
        return lowestNode;
    }

    class NodeDist implements Comparable<NodeDist> {

        NodeDist(String word, int dist) {
            this.word = word;
            this.dist = dist;
        }

        String word;
        int dist;

        @Override
        public int compareTo(NodeDist nodeDist) {
            return dist - nodeDist.dist;
        }
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
