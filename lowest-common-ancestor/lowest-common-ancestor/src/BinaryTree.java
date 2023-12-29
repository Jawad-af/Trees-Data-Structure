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

    public boolean findPath(TreeNode root, int x, List<Integer> path) {
        if (root == null) {
            return false;
        }
        path.add(root.data);
        if (root.data == x) {
            return true;
        }
        if (findPath(root.left, x, path) || findPath(root.right, x, path)) {
            return true;
        }
        path.remove(path.size() - 1);
        return false;
    }
    public boolean findPaths(TreeNode root, int x, int y, List<Integer> xPath, List<Integer> yPath) {
        if (root == null) {
            return false;
        }
        xPath.add(root.data);
        yPath.add(root.data);
        if (root.data == x) {
            return true;
        }
        if (root.data == y) {
            return true;
        }
        boolean xLeftPath = findPaths(root.left, x, y, xPath, yPath);
        boolean xRightPath = findPaths(root.right, x, y, xPath, yPath);
        if (xLeftPath || xRightPath) {
            return true;
        }

        boolean yLeftPath = findPaths(root.left, x, y, xPath, yPath);
        boolean yRightPath = findPaths(root.right, x, y, xPath, yPath);

        xPath.remove(xPath.size() - 1);
        yPath.remove(yPath.size() - 1);
        return false;
    }

    public void findLowestCommonAncestor(TreeNode root, int x, int y) {
        List<Integer> xPath = new ArrayList<>();
        List<Integer> yPath = new ArrayList<>();
        findPath(root, x, xPath);
        findPath(root, y, yPath);
        findPaths(root, x, y, xPath, yPath);

        System.out.println(x + " path");
        for (int i : xPath) {
            System.out.print(i + " ");
        }
        System.out.println(y + " path");
        for (int j : yPath) {
            System.out.print(j + " ");
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
        binaryTree.findLowestCommonAncestor(root, 3, 12);
    }
}
