import java.util.*;

public class BinaryTree {
    static class TreeNode {
        int data;
        TreeNode left, right;
        public TreeNode(int data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    public TreeNode addNode(TreeNode root, int data) {
        if (root == null) {
            return new TreeNode(data);
        }
        if (root.data < data) {
            root.right = addNode(root.right, data);
        } else if ( root.data > data) {
            root.left = addNode(root.left, data);
        }
        return root;
    }

    public void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
    }

    public void preOrder(TreeNode root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public TreeNode buildBinaryTree(Map<Integer, Integer> inOrderMap,
                                    int[] inOrder, int ioStart, int ioEnd,
                                    int[] preOrder, int poStart, int poEnd) {
        if (ioStart > ioEnd || poStart > poEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preOrder[poStart]);
        int rootIndex = inOrderMap.get(preOrder[poStart]);
        int leftSubTreeNumOfNodes = rootIndex - ioStart;
        root.left = buildBinaryTree(inOrderMap,
                inOrder, ioStart, rootIndex - 1,
                preOrder, poStart + 1, poStart + leftSubTreeNumOfNodes);
        root.right = buildBinaryTree(inOrderMap,
                inOrder, rootIndex + 1, ioEnd,
                preOrder, poStart + leftSubTreeNumOfNodes + 1, poEnd);
        return root;
    }

    public TreeNode buildBinaryTree(int[] inOrder, int[] preOrder) {
        Map<Integer, Integer> inOrderMap = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            inOrderMap.put(inOrder[i], i);
        }
        TreeNode root = buildBinaryTree( inOrderMap,
                inOrder, 0, inOrder.length - 1,
                preOrder, 0, preOrder.length - 1);
        return root;
    }

    public static void main(String[] args) {
        /*
                        11
                      /   \
                    2       13
                   / \     / \
                 -1   4   12   14
                 /   / \
               -8   3   9
        */

        // The algorithm solves the problem of the time required to burn
        // a binary tree of any given node
        TreeNode root = new TreeNode(11);
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.addNode(root,2);
        binaryTree.addNode(root,13);
        binaryTree.addNode(root,4);
        binaryTree.addNode(root,3);
        binaryTree.addNode(root,-1);
        binaryTree.addNode(root,-8);
        binaryTree.addNode(root,9);
        binaryTree.addNode(root,12);
        binaryTree.addNode(root,14);

        System.out.print("The inorder traversal of the first tree is: ");
        binaryTree.inOrder(root);
        System.out.print("\nThe preorder traversal of first the tree is: ");
        binaryTree.preOrder(root);

        int[] inOrder = new int[]{ -8, -1, 2, 3, 4, 9, 11, 12, 13, 14 };
        int[] preOrder = new int[]{ 11, 2, -1, -8, 4, 3, 9, 13, 12, 14 };

        TreeNode secondRoot = binaryTree.buildBinaryTree(inOrder, preOrder);
        TreeNode thirdRoot = secondRoot;
        System.out.print("\n\nThe inorder traversal of the second tree is: ");
        binaryTree.inOrder(secondRoot);
        System.out.print("\nThe preorder traversal of the second tree is: ");
        binaryTree.preOrder(secondRoot);

    }
}