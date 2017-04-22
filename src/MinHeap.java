import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Min-Heap implementation
 */
public class MinHeap<T extends Comparable> extends AbstractQueue<T> {

    public MinHeap(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public MinHeap() {
        this.comparator = Comparable::compareTo;
    }

    private final Comparator<T> comparator;
    private ArrayList<T> values = new ArrayList<>();

    @Override
    public boolean add(T v) {
        return this.offer(v);
    }

    @Override
    public boolean offer(T t) {
        values.add(t);
        upHeap(values.size() - 1);
        if (!checkHeapProperty(0)) {
            throw new IllegalStateException();
        }
        return true;
    }

    @Override
    public T peek() {
        return values.get(0);
    }

    @Override
    public T poll() {
        T removed = values.get(0);
        values.set(0, values.get(values.size() - 1));
        values.remove(values.size() - 1);
        downHeap(0);
        if (!checkHeapProperty(0)) {
            throw new IllegalStateException();
        }
        return removed;
    }

    @Override
    public Iterator<T> iterator() {
        return values.iterator();
    }

    @Override
    public int size() {
        return values.size();
    }

    /**
     * Restore heap property after removal of smallest element
     *
     * @param parentIndex
     */
    private void downHeap(int parentIndex) {

        // check if a child is smaller than the parent. If that is the case swap and continue recursively
        int childIndex1 = 2 * parentIndex + 1;
        int childIndex2 = 2 * parentIndex + 2;

        if (childIndex2 < size()) {
            T child1 = values.get(childIndex1);
            T child2 = values.get(childIndex2);
            if (comparator.compare(child1, child2) > 0) {
                // child 1 is smaller, compare with child 1
                if (comparator.compare(values.get(parentIndex), values.get(childIndex1)) < 0) {
                    T swp = values.get(childIndex1);
                    values.set(childIndex1, values.get(parentIndex));
                    values.set(parentIndex, swp);
                    downHeap(childIndex1);
                }
            } else if (comparator.compare(values.get(parentIndex), values.get(childIndex2)) < 0) {
                T swp = values.get(childIndex2);
                values.set(childIndex2, values.get(parentIndex));
                values.set(parentIndex, swp);
                downHeap(childIndex2);
            }
        } else if (childIndex1 < size()) {
            if (comparator.compare(values.get(parentIndex), values.get(childIndex1)) < 0) {
                T swp = values.get(childIndex1);
                values.set(childIndex1, values.get(parentIndex));
                values.set(parentIndex, swp);
            }
        }
    }

    /**
     * Restore heap property after insertion
     *
     * @param pos
     */
    private void upHeap(int pos) {

        // check if parent is smaller
        int parentIndex = (int) Math.floor((pos - 1) / 2);

        if (comparator.compare(values.get(parentIndex), values.get(pos)) < 0) {
            //swap elements and continue with parent
            T swp = values.get(pos);
            values.set(pos, values.get(parentIndex));
            values.set(parentIndex, swp);
            upHeap(parentIndex);
        }
    }

    private boolean checkHeapProperty(int i) {

        if (i >= size()) {
            return true;
        }
        int child1 = 2 * i + 1;
        int child2 = 2 * i + 2;
        if (child1 < size() && comparator.compare(values.get(i), values.get(child1)) < 0) {
            return false;
        } else if (child2 < size() && comparator.compare(values.get(i), values.get(child2)) < 0) {
            return false;
        }
        return checkHeapProperty(child1) && checkHeapProperty(child2);
    }

    /**
     * Test
     *
     * @param args
     */
    public static void main(String[] args) {

        MinHeap<Integer> q = new MinHeap<>((e1, e2) -> e2 - e1);

        q.offer(11);
        q.offer(3);
        q.offer(20);
        q.offer(5);
        q.offer(6);
        q.offer(9);
        q.offer(7);

        System.out.println("print::::::::::");
        while (q.size() > 0) {
            System.out.println(q.poll());
        }
    }
}
