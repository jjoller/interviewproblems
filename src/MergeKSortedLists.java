import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/merge-k-sorted-lists/description/
 * <p>
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 */
public class MergeKSortedLists {
    public static void main(String[] args) {

    }

    class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            PriorityQueue<ListNode> q = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));
            q.addAll(Arrays.stream(lists).filter(Objects::nonNull).collect(Collectors.toList()));
            ListNode merged = null;
            ListNode start = null;
            while (q.size() > 0) {
                ListNode node = q.poll();
                if (node.next != null) {
                    q.add(node.next);
                }
                if (merged == null) {
                    merged = new ListNode(node.val);
                    start = merged;
                } else {
                    merged.next = new ListNode(node.val);
                    merged = merged.next;
                }
            }
            return start;
        }
    }

    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        final int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
