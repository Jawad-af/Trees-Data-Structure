

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

    public TreeNode find(TreeNode root, int data) {
        if (data == root.data) {
            return root;
        }

        if (root.data > data) {
            find(root.left, data);
        } else {
            find(root.right, data);
        }
        return null;
    }

    public void inOrderTraversal(TreeNode root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.print(root.data + " ");
            inOrderTraversal(root.right);
        }
    }

    public int calculateHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = calculateHeight(root.left);
        int right = calculateHeight(root.right);
        return 1 + Math.max(left, right);
    }

    public static void main(String[] args) {
        /*
                        11
                      /   \
                    2       70
                   / \     / \
                 -1   4   66   77
                 /   / \
               -8   3   9
        */
        TreeNode root = new TreeNode(11);
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.addNode(root,2);
        binaryTree.addNode(root,70);
        binaryTree.addNode(root,4);
        binaryTree.addNode(root,3);
        binaryTree.addNode(root,-1);
        binaryTree.addNode(root,-8);
        binaryTree.addNode(root,9);
        binaryTree.addNode(root,77);
        binaryTree.addNode(root,66);
        binaryTree.inOrderTraversal(root);
        System.out.println();
        System.out.println(binaryTree.calculateHeight(root));

    }
}
