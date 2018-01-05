import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class KnapSack {

    public static void main(String[] args) {
        int wt[] = {10, 20, 30};
        int val[] = {60, 100, 120};
        Test.assertEquals(220, knapSack(50, wt, val));
        System.out.println("Tests succeeded");
    }

    static int knapSack(int w, int[] wt, int[] val) {

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
}

