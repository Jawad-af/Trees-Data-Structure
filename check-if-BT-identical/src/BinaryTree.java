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

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(q == null && p == null){
            return true;
        } else if (q == null || p == null){
            return false;
        }
        return (q.data == p.data) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
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
        TreeNode firstRoot = new TreeNode(11);
        BinaryTree firstBinaryTree = new BinaryTree();
        firstBinaryTree.addNode(firstRoot,2);
        firstBinaryTree.addNode(firstRoot,13);
        firstBinaryTree.addNode(firstRoot,4);
        firstBinaryTree.addNode(firstRoot,3);
        firstBinaryTree.addNode(firstRoot,-1);
        firstBinaryTree.addNode(firstRoot,-8);
        firstBinaryTree.addNode(firstRoot,29);
        firstBinaryTree.addNode(firstRoot,99);
        firstBinaryTree.addNode(firstRoot,14);

        TreeNode secondRoot = new TreeNode(11);
        BinaryTree secondBinaryTree = new BinaryTree();
        secondBinaryTree.addNode(secondRoot,2);
        secondBinaryTree.addNode(secondRoot,13);
        secondBinaryTree.addNode(secondRoot,4);
        secondBinaryTree.addNode(secondRoot,3);
        secondBinaryTree.addNode(secondRoot,-1);
        secondBinaryTree.addNode(secondRoot,-8);
        secondBinaryTree.addNode(secondRoot,29);
        secondBinaryTree.addNode(secondRoot,99);
        secondBinaryTree.addNode(secondRoot,14);

        TreeNode thirdRoot = new TreeNode(11);
        BinaryTree thirdBinaryTree = new BinaryTree();
        thirdBinaryTree.addNode(thirdRoot,2);
        thirdBinaryTree.addNode(thirdRoot,13);
        thirdBinaryTree.addNode(thirdRoot,4);
        thirdBinaryTree.addNode(thirdRoot,3);
        thirdBinaryTree.addNode(thirdRoot,-1);
        thirdBinaryTree.addNode(thirdRoot,-9);
        thirdBinaryTree.addNode(thirdRoot,29);
        thirdBinaryTree.addNode(thirdRoot,99);
        thirdBinaryTree.addNode(thirdRoot,14);

        System.out.print("Inorder traversal for the first tree:  ");
        firstBinaryTree.inOrder(firstRoot);
        System.out.print("\nInorder traversal for the second tree: ");
        secondBinaryTree.inOrder(secondRoot);
        System.out.print("\nInorder traversal for the third tree:  ");
        thirdBinaryTree.inOrder(thirdRoot);

        System.out.print("\n\nAre first and second trees identical? ");
        System.out.print(firstBinaryTree.isSameTree(firstRoot, secondRoot));
        System.out.print("\nAre first and third trees identical? ");
        System.out.print(firstBinaryTree.isSameTree(firstRoot, thirdRoot));
    }
}