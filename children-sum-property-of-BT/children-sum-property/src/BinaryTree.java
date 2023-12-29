import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

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
    public void inOrderTraversal(TreeNode root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.print(root.data + " ");
            inOrderTraversal(root.right);
        }
    }

    public int transform(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = transform(root.left);
        int right = transform(root.right);
        if (left != 0 || right != 0) {
            root.data = left + right;
        }
        return root.data;
    }

    public void transformToChildrenPropertyTree(TreeNode root) {
        transform(root);
        inOrderTraversal(root);
    }

    public int check(TreeNode root, boolean[] flag) {
        if (root == null) {
            return 0;
        }
        int left = check(root.left, flag);
        int right = check(root.right, flag);
        if(left != 0 || right != 0){
            if (root.data != left + right) {
                flag[0] = false;
                return root.data;
            }
        }
        return root.data;
    }

    public boolean isChildrenPropertySatisfied(TreeNode root) {
        boolean[] flag = new boolean[1];
        flag[0] = true;
        check(root, flag);
        return flag[0];
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

               THE OUTPUT TREE

                       30
                      /   \
                    4      26
                   / \     / \
                 -8   12 12   14
                 /   / \
               -8   3   9
        */
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
        System.out.println(binaryTree.isChildrenPropertySatisfied(root));
        binaryTree.transformToChildrenPropertyTree(root);
        System.out.println();
        System.out.println(binaryTree.isChildrenPropertySatisfied(root));
    }
}

