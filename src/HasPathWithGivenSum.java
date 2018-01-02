/**
 * https://codefights.com/interview-practice/task/TG4tEMPnAc3PnzRCs
 * <p>
 * Given a binary tree t and an integer s, determine whether there is a root to leaf path in t such that the sum of vertex values equals s.
 */
public class HasPathWithGivenSum {
    boolean hasPathWithGivenSum(Tree<Integer> t, int s) {
        return t == null && s == 0 || (t != null && (t.left == null && t.right == null && t.value == s ||
                t.left != null && hasPathWithGivenSum(t.left, s - t.value) ||
                t.right != null && hasPathWithGivenSum(t.right, s - t.value)));
    }
}

class Tree<T> {
    Tree(T x) {
        value = x;
    }

    T value;
    Tree<T> left;
    Tree<T> right;
}
