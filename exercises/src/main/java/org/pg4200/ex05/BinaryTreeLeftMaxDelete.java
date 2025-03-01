package org.pg4200.ex05;

import org.pg4200.les05.MyMapBinarySearchTree;

public class BinaryTreeLeftMaxDelete<K extends Comparable<K>, V> extends MyMapBinarySearchTree<K,V> {

    @Override
    protected TreeNode delete(K key, TreeNode subtreeRoot){

        // Key is not found - ends up recursive on a null node
        if (subtreeRoot == null) {
            return null;
        }

        int cmp = key.compareTo(subtreeRoot.key);

        if (cmp < 0) {
            subtreeRoot.left = delete(key, subtreeRoot.left);
            return subtreeRoot;
        }

        if (cmp > 0) {
            subtreeRoot.right = delete(key, subtreeRoot.right);
            return subtreeRoot;
        }

        /*
            Here, we are done with the recursion.
            How to delete this node will depend on
            how many children it has
         */

        size--;

        /*
            What we are going to do here depends on the number of children:
            0
            1 (left or right)
            2

            The (2) is the most complex case.
            For the (1), just need to check which child is not-null, and that will become
            the new subtree returned when "this" node is deleted.
            If both children are missing (ie case 0), then the subtree is just "this" node.
            Once deleted, what is left is just a null subtree.
            So we return null.
            However, we do not need to explicitely have a check like
            "if(subtreeRoot.left == null && subtreeRoot.right == null) return null"
            as the following check would give the same result (ie returning null because
            subtreeRoot.right is null) even in the (0) case.
         */

        if (subtreeRoot.left == null) {
            return subtreeRoot.right;
        }

        if (subtreeRoot.right == null) {
            return subtreeRoot.left;
        }

        /*
            Both children are present
         */

        TreeNode tmp = subtreeRoot;
        subtreeRoot = max(tmp.left);
        subtreeRoot.left = deleteMax(tmp.left);
        subtreeRoot.right = tmp.right;

        return subtreeRoot;
    }

    protected TreeNode max(TreeNode subtreeRoot) {
        if (subtreeRoot.right == null) {
            return subtreeRoot;
        }
        return max(subtreeRoot.right);
    }

    protected TreeNode deleteMax(TreeNode subtreeRoot) {

        if (subtreeRoot.right == null) {
            return subtreeRoot.left;
        }

        subtreeRoot.right = deleteMax(subtreeRoot.right);

        return subtreeRoot;
    }
}
