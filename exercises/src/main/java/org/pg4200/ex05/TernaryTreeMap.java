package org.pg4200.ex05;

import com.sun.source.tree.Tree;
import org.pg4200.les05.MyMapBinarySearchTree;
import org.pg4200.les05.MyMapTreeBased;

import java.util.Objects;

public class TernaryTreeMap<K extends Comparable<K>, V> implements MyMapTreeBased<K, V> {

    protected class TreeNode {
        public K firstKey;
        public K secondKey;

        public V firstValue;
        public V secondValue;

        public TreeNode left;
        public TreeNode middle;
        public TreeNode right;
    }

    protected TreeNode root;

    protected int size;

    @Override
    public void put(K key, V value) {
        Objects.requireNonNull(key);
        root = put(key, value, root);
    }

    private TreeNode put(K key, V value, TreeNode subtree) {
        if (subtree == null) {
            TreeNode node = new TreeNode();
            node.firstKey = key;
            node.firstValue = value;
            size++;
            return node;
        }

        if(subtree.secondKey == null && subtree.firstKey != null){
            if (key.compareTo(subtree.firstKey) < 0){
                subtree.secondKey = subtree.firstKey;
                subtree.secondValue = subtree.firstValue;

                subtree.firstKey = key;
                subtree.firstValue = value;

                return subtree;
            }
        }

        int firstCmp = key.compareTo(subtree.firstKey);
        int secondCmp = key.compareTo(subtree.secondKey);

        if (firstCmp < 0) {
            subtree.left = put(key, value, subtree.left);
            return subtree;
        }

        if ((firstCmp > 0) && (secondCmp < 0)) {
            subtree.middle = put(key, value, subtree.middle);
            return subtree;
        }

        if (secondCmp > 0) {
            subtree.left = put(key, value, subtree.left);
            return subtree;
        }

        subtree.firstValue = value;

        return subtree;
    }

    @Override
    public void delete(K key) {

    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int getMaxTreeDepth() {
        return 0;
    }
}
