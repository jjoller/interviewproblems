import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HighestProduct {

    public static void main(String[] args) {

        ArrayList<Integer> a = new ArrayList<>();
        a.add(0);
        a.add(-1);
        a.add(3);
        a.add(100);
        a.add(-70);
        a.add(-50);

        System.out.println(new HighestProduct().maxp3(a));
    }

    public int maxp3(ArrayList<Integer> a) {

        int n = a.size();
        Collections.sort(a);

        List<Integer> sums = new ArrayList<>();
        sums.add(a.get(0) * a.get(1) * a.get(2));
        sums.add(a.get(n - 3) * a.get(n - 2) * a.get(n - 1));
        sums.add(a.get(0) * a.get(n - 2) * a.get(n - 1));
        sums.add(a.get(0) * a.get(1) * a.get(n - 1));

        return sums.stream().max(Comparator.comparingInt(i -> i)).get();
    }

    public int majorityElement(final List<Integer> a) {

        Map<Integer, Integer> counts = a.stream()
                .map((e) -> Collections.singletonMap(e, 1))
                .reduce((m1, m2) -> {
                    Map<Integer, Integer> m = new HashMap<>(m1);
                    m2.entrySet().forEach(entry -> m.merge(entry.getKey(), entry.getValue(), (e1, e2) -> e1 + e2));
                    return m;
                }).get();

        return counts.entrySet().stream().max((e1, e2) -> e1.getValue() - e2.getValue()).get().getKey();
    }

}
