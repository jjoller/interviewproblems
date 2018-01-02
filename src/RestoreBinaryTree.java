import java.util.ArrayList;
import java.util.List;

/**
 * https://codefights.com/interview-practice/task/AaWaYxi8gjtbqgp2M
 * <p>
 * Let's define inorder and preorder traversals of a binary tree as follows:
 * <p>
 * Inorder traversal first visits the left subtree, then the root, then its right subtree;
 * Preorder traversal first visits the root, then its left subtree, then its right subtree.
 * For example, if tree looks like this:
 * <p>
 * 1
 * / \
 * 2   3
 * /   / \
 * 4   5   6
 * then the traversals will be as follows:
 * <p>
 * Inorder traversal: [4, 2, 1, 5, 3, 6]
 * Preorder traversal: [1, 2, 4, 3, 5, 6]
 * Given the inorder and preorder traversals of a binary tree t, but not t itself, restore t and return it.
 */
public class RestoreBinaryTree {

    public static void main(String[] args){
        int[] inorder = {4, 2, 1, 5, 3, 6};
        int[] preorder = {1, 2, 4, 3, 5, 6};
        RestoreBinaryTree r = new RestoreBinaryTree();
        Tree<Integer> tree = r.restoreBinaryTree(inorder,preorder);

    }

    Tree<Integer> restoreBinaryTree(int[] inorder, int[] preorder) {
        if (inorder == null) {
            return null;
        }
        if (inorder.length != preorder.length) {
            throw new IllegalArgumentException();
        }
        List<Integer> i = new ArrayList<>();
        List<Integer> p = new ArrayList<>();
        for (int e : inorder)
            i.add(e);
        for (int e : preorder)
            p.add(e);
        return restoreBinaryTree(i, p);
    }

    Tree<Integer> restoreBinaryTree(List<Integer> inorder, List<Integer> preorder) {
        if (inorder.size() != preorder.size()) {
            throw new IllegalArgumentException();
        }
        if (inorder.size() == 0) {
            return null;
        }
        List<Integer> leftInorder = new ArrayList<>();
        List<Integer> leftPreorder = new ArrayList<>();
        List<Integer> rightInorder = new ArrayList<>();
        List<Integer> rightPreorder = new ArrayList<>();
        boolean left = true;
        for (int i = 0; i < inorder.size(); i++) {
            if (inorder.get(i).equals(preorder.get(0))) {
                left = false;
            } else {
                if (left) {
                    leftInorder.add(inorder.get(i));
                    leftPreorder.add(preorder.get(i + 1));
                } else {
                    rightInorder.add(inorder.get(i));
                    rightPreorder.add(preorder.get(i));
                }
            }
        }
        Tree<Integer> leftTree = restoreBinaryTree(leftInorder, leftPreorder);
        Tree<Integer> rightTree = restoreBinaryTree(rightInorder, rightPreorder);
        Tree<Integer> result = new Tree<>(preorder.get(0));
        result.left = leftTree;
        result.right = rightTree;
        return result;
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
