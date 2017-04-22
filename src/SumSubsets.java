import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://codefights.com/interview/kEgA4DXcfXuriqGru
 */
public class SumSubsets {

    public static void main(String[] args) {

        SumSubsets s = new SumSubsets();
        int[] arr = {1, 2, 3};
        System.out.println(s.toString(s.sumSubsets(arr, 1)));
    }

    int[][] sumSubsets(int[] arr, int num) {

        boolean[][] a = new boolean[arr.length + 1][num + 1];

        // handle zero length array
        if (arr.length == 0) {
            return new int[1][0];
        }

        a[0][0] = true;

        for (int i = 0; i < arr.length; i++) {
            int addend = arr[i];
            for (int j = 0; j <= num; j++) {
                if (a[i][j]) {
                    a[i + 1][j] = true;
                    if (j + addend <= num) {
                        a[i + 1][j + addend] = true;
                    }
                }
            }
        }

        printArray(a);

        // backtrack
        List<List<Integer>> toReturn = backtrack(arr.length, num, a, arr);

        List<List<Integer>> paths = new ArrayList<>();
        Set<String> duplicates = new HashSet<>();

        for (List<Integer> l : toReturn) {
            String key = key(l);
            if (!duplicates.contains(key)) {
                duplicates.add(key);
                paths.add(l);
            }
        }

        Collections.sort(paths, (l1, l2) -> {
            for (int i = 0; i < Math.min(l1.size(), l2.size()); i++) {
                if (!Objects.equals(l1.get(i), l2.get(i))) {
                    return l1.get(i) - l2.get(i);
                }
            }
            return l1.size() - l2.size();
        });

        // convert to int[][]
        int[][] result = new int[paths.size()][];
        for (int i = 0; i < paths.size(); i++) {
            result[i] = new int[paths.get(i).size()];
            for (int j = 0; j < paths.get(i).size(); j++) {
                result[i][j] = paths.get(i).get(j);
            }
        }
        return result;
    }

    String key(List<Integer> a) {
        String s = "";
        for (int i : a) {
            s += i + ",";
        }
        return s;
    }

    /**
     * Returns the possible paths back to 0
     */
    List<List<Integer>> backtrack(int i, int j, boolean[][] a, int[] arr) {
        List<List<Integer>> result;
        if (i > 0) {
            int addend = arr[i - 1];
            List<List<Integer>> l1 = null;
            List<List<Integer>> l2 = null;
            if (a[i - 1][j]) {
                l1 = backtrack(i - 1, j, a, arr);
            }
            if (j - addend >= 0) {
                if (a[i - 1][j - addend]) {
                    l2 = backtrack(i - 1, j - addend, a, arr);
                    for (List<Integer> list : l2) {
                        list.add(addend);
                    }
                }
            }
            result = merge(l1, l2);
        } else {
            result = new ArrayList<>();
            result.add(new ArrayList<>());
        }
        return result;
    }

    List<List<Integer>> merge(List<List<Integer>> l1, List<List<Integer>> l2) {
        List<List<Integer>> merged = new ArrayList<>();
        if (l1 != null)
            merged.addAll(l1);
        if (l2 != null)
            merged.addAll(l2);
        return merged;
    }

    String toString(int[][] a) {
        List<String> inners = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            List<String> inner = of(a[i]).map(element -> Arrays.toString(element) + "").collect(Collectors.toList());
            inners.add(String.join(", ", inner) + "\n");
        }
        String s = "[" + String.join(", ", inners) + "]";
        return s;
    }

    void printArray(boolean[][] a) {
        List<String> inners = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            List<String> inner = of(a[i]).map(element -> Arrays.toString(element) + "").collect(Collectors.toList());
            inners.add(String.join(", ", inner) + "\n");
        }
        String s = "[" + String.join(", ", inners) + "]";
        System.out.println(s);
    }

    <T> Stream<T> of(T... values) {
        return Arrays.stream(values);
    }


}
