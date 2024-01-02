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

    public TreeNode assignParents(TreeNode root, Map<Integer, TreeNode> parents) {
        if (root == null) {
            return null;
        }
        TreeNode left = assignParents(root.left, parents);
        TreeNode right = assignParents(root.right, parents);
        if (left != null) {
            parents.put(left.data, root);
        }
        if (right != null) {
            parents.put(right.data, root);
        }
        return root;
    }

    public TreeNode findNodePosition(TreeNode root, int node) {
        if (root == null || root.data == node) {
            return root;
        }
        TreeNode left = findNodePosition(root.left, node);
        TreeNode right = findNodePosition(root.right, node);
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        return root;
    }
    public int calculateBurnTime(TreeNode root, int node) {
        Map<Integer, TreeNode> parents = new HashMap<>();
        assignParents(root, parents);
        TreeNode nodePosition = findNodePosition(root, node);
        if (nodePosition == null) {
            System.out.println("The node " + node + " does not exits in the tree!");
            return 0;
        }

        int time = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        Map<Integer, Boolean> visited = new HashMap<>();

        queue.add(nodePosition);
        visited.put(nodePosition.data, true);

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean burn = false;
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                TreeNode[] neighbors = new TreeNode[3];
                neighbors[0] = current.left;
                neighbors[1] = current.right;
                neighbors[2] = parents.get(current.data);
                for (int j = 0; j < 3; j++) {
                    if (neighbors[j] != null && !visited.getOrDefault(neighbors[j].data, false)) {
                        burn = true;
                        queue.add(neighbors[j]);
                        visited.put(neighbors[j].data, true);
                    }
                }
            }
            if(burn) time++;
        }
        return time;
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

        int time = binaryTree.calculateBurnTime(root, 14);
        System.out.println("The time required to burn the tree is: " + time);

    }
}