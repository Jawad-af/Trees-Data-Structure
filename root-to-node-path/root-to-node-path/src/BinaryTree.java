import java.util.ArrayList;
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

    public boolean findPath(TreeNode root, int nodeValue, List<Integer> path) {
        if (root == null) {
            return false;
        }
        path.add(root.data);
        if (root.data == nodeValue) {
            return true;
        }
        boolean isLeft = findPath(root.left, nodeValue, path);
        boolean isRight = findPath(root.right, nodeValue, path);
        if (isRight || isLeft) {
            return true;
        }
        path.remove(path.size() - 1);
        return false;
    }

    public void printRootToNodePath(TreeNode root, int nodeValue) {
        List<Integer> path = new ArrayList<>();
        findPath(root, nodeValue, path);
        for (int i : path) {
            System.out.print(i + " ");
        }
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
        System.out.print("Inorder traversal: ");
        binaryTree.inOrderTraversal(root);
        System.out.println();

        int node = 12;
        System.out.print("\nThe path from the root to node " + node + " is: ");
        binaryTree.printRootToNodePath(root, node);

        node = 3;
        System.out.print("\nThe path from the root to node " + node + " is: ");
        binaryTree.printRootToNodePath(root, node);

        node = -1;
        System.out.print("\nThe path from the root to node " + node + " is: ");
        binaryTree.printRootToNodePath(root, node);

        node = 9;
        System.out.print("\nThe path from the root to node " + node + " is: ");
        binaryTree.printRootToNodePath(root, node);
    }
}
