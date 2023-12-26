#include <stdio.h>
#include <stdlib.h>

struct TreeNode {
    int data;
    struct TreeNode* left;
    struct TreeNode* right;
};

// implementing the inorder iterative traversal function using threaded backtracking
void inOrderIterativeTraversal(struct TreeNode* root)
{
    struct TreeNode* currentNode = root;
    while (currentNode != NULL)
    {
        if (currentNode->left == NULL)
        {
            printf("%d  ", currentNode->data);
            currentNode = currentNode->right;
        }
        else {
            struct TreeNode* prev = currentNode->left;
            while (prev->right != NULL && prev->right != currentNode)
            {
                prev = prev->right;
            }

            if (prev->right == NULL)
            {
                prev->right = currentNode;
                currentNode = currentNode->left;
            }
            else {
                prev->right = NULL;
                printf("%d  ", currentNode->data);
                currentNode = currentNode->right;
            }
        }
    }

}

// allocate a new node
struct TreeNode* newtNode(int data) {
    struct TreeNode* tNode = (struct TreeNode*)malloc(sizeof(struct TreeNode));
    tNode->data = data;
    tNode->left = NULL;
    tNode->right = NULL;

    return tNode;
}

struct TreeNode* insertNode(struct TreeNode* root, int data) {
    if (root == NULL) {
        return newtNode(data);
    }

    if (data < root->data) {
        root->left = insertNode(root->left, data);
    }
    else if (data > root->data) {
        root->right = insertNode(root->right, data);
    }

    return root;
}

// Function to generate a binary tree of a specified size
struct TreeNode* generateBinaryTree(int size) {
    struct TreeNode* root = NULL;

    srand(50);

    for (int i = 0; i < size; ++i) {
        int data = rand() % 50;
        root = insertNode(root, data);
    }
    return root;
}

void prettyPrint(struct TreeNode* root, int space) {
    if (root == NULL) {
        return;
    }

    space += 5;

    prettyPrint(root->right, space);

    printf("\n");
    for (int i = 5; i < space; i++)
        printf("-");
    printf("%d\n", root->data);

    prettyPrint(root->left, space);
}

int main() {
    struct TreeNode* root = generateBinaryTree(50);

    printf("\n\nPretty Print of the Binary Tree:\n\n");
    prettyPrint(root, 0);

    printf("\n\nIn-Order traversal:\n\n");
    inOrderIterativeTraversal(root);

    return 0;
}