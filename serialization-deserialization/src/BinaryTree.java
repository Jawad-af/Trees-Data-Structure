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

    public boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }
    public String serialize(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        String serialized = new String();
        queue.add(root);
        serialized = serialized + root.data + " ";
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                TreeNode leftNode = current.left;
                TreeNode rightNode = current.right;
                if (leftNode != null) {
                    queue.add(leftNode);
                    serialized = serialized + leftNode.data + " ";
                } else if (leftNode == null && !isLeafNode(current)) {
                    serialized = serialized + "null ";
                }
                if (rightNode != null ) {
                    queue.add(rightNode);
                    serialized = serialized + rightNode.data + " ";
                } else if (rightNode == null && !isLeafNode(current)) {
                    serialized = serialized + "null ";
                }
            }
        }
        return serialized;
    }

    public TreeNode buildTree(String[] data, int dataLength, int index) {
        if (index + 2 > dataLength || data[index] == "null") {
            return null;
        } else {
            TreeNode root = new TreeNode(Integer.parseInt(data[index]));
            root.left = new TreeNode(Integer.parseInt(data[index + 1]));
            root.right = new TreeNode(Integer.parseInt(data[index + 2]));
            buildTree(data, dataLength, index + 1);
            return root;
        }
    }
    public TreeNode deserialize(String data) {
        String[] splittedString = data.split(" ");
        int dataLength = splittedString.length - 1;
        int index = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        Map<Integer, TreeNode> created = new HashMap<>();




        //created.put(createNode.data, createNode);
        //queue.add(createNode);

        for (int i = 0; i < dataLength; i++) {
            String rootValue = splittedString[i];
            int numericalRootValue = Integer.parseInt(rootValue);
            TreeNode createNode = new TreeNode(numericalRootValue);
        }
        return new TreeNode(2);
    }


    public static void main(String[] args) {
        /*
                        11
                      /    \
                    2       13
                   / \      / \
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
        String serialized = binaryTree.serialize(root);
        System.out.println(serialized);

        //TreeNode root2 = binaryTree.deserialize(serialized);
        //binaryTree.inOrder(root2);
    }
}