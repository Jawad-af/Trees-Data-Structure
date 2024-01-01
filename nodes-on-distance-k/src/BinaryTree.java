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
    public TreeNode setParents(TreeNode root, Map<Integer, TreeNode> parents) {
        if (root == null) {
            return null;
        }
        else {
            TreeNode left = setParents(root.left, parents);
            TreeNode right = setParents(root.right, parents);
            if (left != null) {
                parents.put(root.left.data, root);
            }
            if (right != null) {
                parents.put(root.right.data, root);
            }
            return root;
        }
    }
    public TreeNode getNodePosition(TreeNode root, int nodeValue) {
        if (root == null || root.data == nodeValue) {
            return root;
        }
        TreeNode left = getNodePosition(root.left, nodeValue);
        if (left != null) {
            return left;
        }
        return getNodePosition(root.right, nodeValue);
    }
    public ArrayList<Integer> getDistanceNodes(TreeNode root, int node, int distance) {

        ArrayList<Integer> result = new ArrayList<>();

        Map<Integer, TreeNode> parents = new HashMap<>();
        setParents(root, parents);
        TreeNode nodeAddress = getNodePosition(root, node);
        if (nodeAddress == null) {
            System.out.println("The node " + node + " does not exist in the tree!");
            return result;
        }

        int currentDistance = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<TreeNode> visited = new ArrayList<>();
        queue.add(nodeAddress);
        visited.add(nodeAddress);
        while (!queue.isEmpty()) {
            int size = queue.size();
            currentDistance++;
            TreeNode[] neighbors = new TreeNode[3];
            for (int i = 0; i < size; i++) {
                TreeNode dequeued = queue.poll();
                neighbors[0] = dequeued.left;
                neighbors[1] = dequeued.right;
                neighbors[2] = parents.get(dequeued.data);
                for (TreeNode neighbor : neighbors) {
                    if (neighbor != null && !visited.contains(neighbor)) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            if (currentDistance == distance) {
                while (!queue.isEmpty()) {
                    result.add(queue.poll().data);
                }
            }
        }
        return result;
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

        int distance = 3;
        int node = 11;
        ArrayList<Integer> nodes = binaryTree.getDistanceNodes(root, node, distance);
        System.out.print("The nodes on distance " + distance + " for node " + node + " are: [ ");
        for (int i : nodes) {
            System.out.print(i + "  ");
        }
        System.out.print("]");

        System.out.println();
        int distance2 = 3;
        int node2 = 2;
        nodes.clear();
        nodes = binaryTree.getDistanceNodes(root, node2, distance2);
        System.out.print("The nodes on distance " + distance2 + " for node " + node2 + " are: [ ");
        for (int i : nodes) {
            System.out.print(i + "  ");
        }
        System.out.print("]");
    }
}

