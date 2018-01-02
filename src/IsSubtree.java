/**
 * https://codefights.com/interview-practice/task/mDpAJnDQkJqaYYsCg
 * <p>
 * Given two binary trees t1 and t2, determine whether the second tree is a subtree of the first tree. A subtree for
 * vertex v in a binary tree t is a tree consisting of v and all its descendants in t. Determine whether or not there
 * is a vertex v (possibly none) in tree t1 such that a subtree for vertex v (possibly empty) in t1 equals t2.
 */
public class IsSubtree {

    boolean isSubtree(Tree<Integer> t1, Tree<Integer> t2) {
        return t1 == null && t2 == null ||
                matches(t1, t2) || t1 != null && (isSubtree(t1.left, t2) || isSubtree(t1.right, t2));
    }

    boolean matches(Tree<Integer> t1, Tree<Integer> t2) {
        return t1 == null && t2 == null || t1 != null && t2 != null && t1.value.equals(t2.value) && matches(t1.left, t2.left) && matches(t1.right, t2.right);
    }

    class Tree<T> {
        Tree(T x) {
            value = x;
        }

        T value;
        Tree<T> left;
        Tree<T> right;
    }
}
