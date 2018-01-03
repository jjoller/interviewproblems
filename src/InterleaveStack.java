import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Given a stack of N elements, interleave the first half of the stack with the second half reversed using only one other queue. This should be done in-place.
 * <p>
 * Recall that you can only push or pop from a stack, and enqueue or dequeue from a queue.
 * <p>
 * For example, if the stack is [1, 2, 3, 4, 5], it should become [1, 5, 2, 4, 3]. If the stack is [1, 2, 3, 4], it should become [1, 4, 2, 3].
 */
public class InterleaveStack {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        stack.addAll(Arrays.asList(4, 3, 2, 1));
        new InterleaveStack().interleaveStack(stack);
        Test.assertEquals(4, stack.size());
        Test.assertEquals(1, stack.pop());
        Test.assertEquals(4, stack.pop());
        Test.assertEquals(2, stack.pop());
        Test.assertEquals(3, stack.pop());

        stack.clear();
        stack.addAll(Arrays.asList(2, 1));
        new InterleaveStack().interleaveStack(stack);
        Test.assertEquals(2, stack.size());
        Test.assertEquals(1, stack.pop());
        Test.assertEquals(2, stack.pop());

        stack.clear();
        stack.addAll(Arrays.asList(6, 5, 4, 3, 2, 1));
        new InterleaveStack().interleaveStack(stack);
        Test.assertEquals(6, stack.size());
        Test.assertEquals(1, stack.pop());
        Test.assertEquals(6, stack.pop());
        Test.assertEquals(2, stack.pop());
        Test.assertEquals(5, stack.pop());
        Test.assertEquals(3, stack.pop());
        Test.assertEquals(4, stack.pop());

        stack.clear();
        stack.addAll(Arrays.asList(5, 4, 3, 2, 1));
        new InterleaveStack().interleaveStack(stack);
        Test.assertEquals(5, stack.size());
        Test.assertEquals(1, stack.pop());
        Test.assertEquals(5, stack.pop());
        Test.assertEquals(2, stack.pop());
        Test.assertEquals(4, stack.pop());
        Test.assertEquals(3, stack.pop());

        System.out.println("Tests succeeded");
    }

    void interleaveStack(Stack<Integer> s) {
        Queue<Integer> q = new LinkedList<>();
        int n = s.size();
        while (s.size() > 0) {
            q.add(s.pop());
        }

        int skip = n - 2;
        while (skip > 0) {
            s.add(q.poll());
            for (int i = 0; i < skip; i++) {
                q.add(q.poll());
            }
            s.add(q.poll());
            skip -= 2;
        }
        while (q.size() > 0) {
            s.add(q.poll());
        }

        q.clear();
        while (s.size() > 0) {
            q.add(s.pop());
        }
        while (q.size() > 0) {
            s.add(q.poll());
        }
    }
}
