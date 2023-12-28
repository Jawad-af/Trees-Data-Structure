import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    public List<int[]> traverse(TreeNode root, List<int[]> result, Queue<TreeNode> queue, boolean flag) {
        if (root == null) {
            return result;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int[] temp = new int[size];
            for (int i = 0; i < size; i++) {
                TreeNode dequeued = queue.poll();
                int index = flag ? i : (size - 1) - i;
                temp[index] = dequeued.data;
                    if (dequeued.right != null) {
                        queue.add(dequeued.right);
                    }
                    if (dequeued.left != null) {
                        queue.add(dequeued.left);
                    }
            }
            result.add(temp);
            flag = !flag;
        }
        return result;
    }
    public List<int[]> zigzagLevelTraversal(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<int[]> result = new ArrayList<>();
        boolean flag = true; // true indicates that we are traversing from right to left
        traverse(root, result, queue, flag);
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
        System.out.print("Inorder traversal: ");
        binaryTree.inOrderTraversal(root);
        System.out.println();
        System.out.print("Zigzag traversal: ");
        List<int[]> result = binaryTree.zigzagLevelTraversal(root);

        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).length; j++) {
                System.out.print(result.get(i)[j] + " ");
            }
        }
    }
}
