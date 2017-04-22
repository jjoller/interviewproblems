import java.util.PriorityQueue;

public class DataStructures {

    public static void main(String[] args) {

        PriorityQueue<El> coll = new PriorityQueue<>();
        //SortedSet<El> sortedSet = new TreeSet<>();
        coll.add(new El(6, 1));
        coll.add(new El(3, 1));
        coll.add(new El(8, 1));
        coll.add(new El(5, 1));
        coll.add(new El(7, 1));
        coll.add(new El(9, 1));

        //sortedSet.headSet(new El(5, 1)).forEach(System.out::println);


        coll.forEach(e -> System.out.println(e));

    }

    public static class El implements Comparable<El> {

        public El(int key, int val) {
            this.key = key;
            this.val = val;
        }

        private final int key, val;

        public int getKey() {
            return key;
        }

        public int getVal() {
            return val;
        }

        @Override
        public String toString() {
            return "El{" +
                    "key=" + key +
                    '}';
        }

        @Override
        public int compareTo(El other) {
            return this.key - other.key;
        }
    }

}
