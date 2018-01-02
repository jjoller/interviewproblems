/**
 * https://codefights.com/interview-practice/task/jAKLMWLu8ynBhYsv6
 * <p>
 * A tree is considered a binary search tree (BST) if for each of its nodes the following is true:
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and the right subtrees must also be binary search trees.
 * Given a binary search tree t, find the kth smallest element in it.
 * <p>
 * Note that kth smallest element means kth element in increasing order. See examples for better understanding.
 */
public class KthSmallestInBST {
    int kthSmallestInBST(Tree<Integer> t, int k) {
        int n = numElements(t.left);
        if (n + 1 < k) {
            return kthSmallestInBST(t.right, k - n - 1);
        } else if(n + 1 == k) {
            return t.value;
        }else{
            return kthSmallestInBST(t.left, k);
        }
    }

    int numElements(Tree<Integer> t) {
        if (t == null) {
            return 0;
        } else {
            return 1 + numElements(t.left) + numElements(t.right);
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
}
