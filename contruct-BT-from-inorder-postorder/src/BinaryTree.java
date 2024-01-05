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

    public void postOrder(TreeNode root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data + " ");
        }
    }

    public TreeNode buildTree(int[] inOrder, int inSt, int inEnd, int[] postOrder, int postSt, int postEnd, Map<Integer, Integer> inOrderMap){
        if(postEnd < postSt || inSt > inEnd){
            return null;
        }
        int rootValue = postOrder[postEnd];
        int rootIndex = inOrderMap.get(rootValue);
        TreeNode root = new TreeNode(rootValue);
        int rightSubTree = inEnd - rootIndex;
        root.left = buildTree(inOrder, inSt, rootIndex - 1, postOrder, postSt, postEnd - rightSubTree - 1, inOrderMap);
        root.right = buildTree(inOrder, rootIndex + 1, inEnd, postOrder, postEnd - rightSubTree, postEnd -1, inOrderMap);
        return root;
    }
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inOrderMap = new HashMap<>();
        for(int i = 0; i < inorder.length; i++){
            inOrderMap.put(inorder[i], i);
        }
        TreeNode root = buildTree(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1, inOrderMap);
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
        System.out.print("\nThe postOrder traversal of first the tree is: ");
        binaryTree.postOrder(root);

        int[] inOrder = new int[]{ -8, -1, 2, 3, 4, 9, 11, 12, 13, 14 };
        int[] postOrder = new int[]{ -8, -1, 3, 9, 4, 2, 12, 14, 13, 11 };

        TreeNode secondRoot = binaryTree.buildTree(inOrder, postOrder);
        TreeNode thirdRoot = secondRoot;
        System.out.print("\n\nThe inorder traversal of the second tree is: ");
        binaryTree.inOrder(secondRoot);
        System.out.print("\nThe preorder traversal of the second tree is: ");
        binaryTree.postOrder(secondRoot);

    }
}