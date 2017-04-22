import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/lru-cache/#/description
 */
public class LRUCache {

    public static void main(String[] args) {

        PriorityQueue<Integer> q = new PriorityQueue<>();

        q.add(5);
        System.out.println(q.size());
        q.add(5);
        System.out.println(q.size());
        q.remove(5);
        System.out.println(q.size());



        LRUCache c = new LRUCache(2);

        /*cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4

        System.out.println(c.get(2));
        System.out.println(c.get(2));
        c.put(2,6);
        System.out.println(c.get(1));
        c.put(1,5);
        c.put(1,2);
        System.out.println(c.get(1));
        System.out.println(c.get(2));*/



    }

    public LRUCache(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
    }

    final int capacity;
    private int index = 0;
    final PriorityQueue<CacheEntry> queue = new PriorityQueue<>();
    final Map<Integer, CacheEntry> map = new HashMap<>();

    public int get(int key) {
        int value = -1;
        if (map.containsKey(key)) {
            CacheEntry entry = map.get(key);
            value = entry.value;
            refreshCache(key, value);
        }
        return value;
    }

    public void put(int key, int value) {
        if (map.size() >= capacity && !map.containsKey(key)) {
            CacheEntry entry = queue.remove();
            map.remove(entry.key);
        }
        map.put(key, refreshCache(key, value));
    }

    private CacheEntry refreshCache(int key, int value) {
        if (map.containsKey(key)) {
            CacheEntry entry = map.get(key);
            queue.remove(entry);
        }
        CacheEntry entry = new CacheEntry(index++, key, value);
        queue.add(entry);
        return entry;
    }

    class CacheEntry implements Comparable<CacheEntry> {

        public CacheEntry(int index, int key, int value) {
            this.index = index;
            this.key = key;
            this.value = value;
        }

        final int index, key, value;

        @Override
        public int compareTo(CacheEntry o) {
            return index - o.index;
        }
    }
}