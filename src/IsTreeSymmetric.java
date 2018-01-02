public class IsTreeSymmetric {

    boolean isTreeSymmetric(Tree<Integer> t) {
        return t == null || isSymmetric(t.left, t.right);
    }

    boolean isSymmetric(Tree<Integer> left, Tree<Integer> right) {
        return left == null && right == null ||
                left != null && right != null &&
                        left.value.equals(right.value) &&
                        isSymmetric(left.left, right.right) &&
                        isSymmetric(left.right, right.left);
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

