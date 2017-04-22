import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class KnapSack {

    public static void main(String[] args) {

        int val[] = new int[]{60, 100, 120};
        int wt[] = new int[]{10, 20, 30};

        System.out.println(knapSack(50, wt, val));

    }

    public static int knapSack(int w, int[] wt, int[] val) {

        int n = wt.length;
        Map<Integer, Integer> a = new HashMap<>();
        a.put(0, 0);
        int max = 0;
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                for (Entry<Integer, Integer> e : new ArrayList<>(a.entrySet())) {
                    int weight = e.getKey() + wt[i];
                    int value = e.getValue() + val[i];
                    if (weight <= w) {
                        int oldVal = Integer.MIN_VALUE;
                        if (a.containsKey(weight)) {
                            oldVal = a.get(weight);
                        }
                        max = Math.max(value, max);
                        a.put(weight, Math.max(value, oldVal));
                    }
                }
            }
        }
        return max;
    }


    public static int knapSack2(int w, int[] wt, int[] val) {

        int n = wt.length;
        if (w <= 0 || n <= 0) {
            return 0;
        }

        int[][] a = new int[n + 1][w + 1];

        for (int i = 1; i <= n; i++) {
            int weight = wt[i - 1];
            int value = val[i - 1];
            for (int j = 0; j <= w; j++) {
                a[i][j] = Math.max(a[i][j], a[i - 1][j]);
                if (j + weight <= w) {
                    int w1 = a[i - 1][j + weight];
                    int w2 = a[i - 1][j] + value;
                    a[i][j + weight] = Math.max(w1, w2);
                }
            }
        }
        return a[n][w];
    }
}

