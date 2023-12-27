

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


    public int maxPathHelper(TreeNode root, int[] maxSum){
        if(root == null){
            return 0;
        }

        int left = maxPathHelper(root.left, maxSum);
        int right = maxPathHelper(root.right, maxSum);
        maxSum[0] = Math.max(maxSum[0], root.data + left + right);
        return root.data + Math.max(left, right);
    }

    public int maxPathSum(TreeNode root) {
        int[] maxPathSum = new int[1];
        maxPathHelper(root, maxPathSum);
        return maxPathSum[0];
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
        binaryTree.inOrderTraversal(root);
        System.out.println();
        System.out.print("The diameter is : " + binaryTree.maxPathSum(root));
    }
}
