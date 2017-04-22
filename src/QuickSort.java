import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class QuickSort {

    public static void main(String[] args) {

        int n = 100;
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            numbers.add(random.nextInt());
        }

        quicksort(0, n - 1, numbers);

        System.out.println(numbers);

        //System.out.println(String.join(",\n", numbers.stream().map(i -> i + "").collect(Collectors.toList())));
    }

    static void quicksort(int low, int high, List<Integer> numbers) {
        int i = low, j = high;
        int pivot = numbers.get(low + (high - low) / 2);
        while (i <= j) {
            while (numbers.get(i) < pivot) {
                i++;
            }
            while (numbers.get(j) > pivot) {
                j--;
            }
            if (i <= j) {
                int temp = numbers.get(i);
                numbers.set(i, numbers.get(j));
                numbers.set(j, temp);
                i++;
                j--;
            }
        }
        if (low < j)
            quicksort(low, j, numbers);
        if (i < high)
            quicksort(i, high, numbers);
    }
}
