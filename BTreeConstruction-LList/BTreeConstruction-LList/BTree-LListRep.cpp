#include <stdlib.h>
#include <stdio.h>
#include <limits.h>
#include <stdbool.h>

struct node {
	struct node* leftChild;
	int data;
	struct node* rightChild;
};

struct queue {
	int size;
	int front;
	int rear;
	struct node** arr;
};

typedef struct node node;
typedef struct queue queue;

node* createNode(int data)
{
	node* newNode = (node*)malloc(sizeof(node));
	newNode->data = data;
	newNode->leftChild = newNode->rightChild = NULL;
	return newNode;
}

queue* createQueue(int size)
{
	queue* q = (queue*)malloc(sizeof(queue));
	q->front = q->rear = -1;
	q->size = size;
	q->arr = (node**)malloc(size * sizeof(node));
	return q;
}

void enqueueNode(queue* q, node* node)
{
	if ((q->rear + 1) % q->size == q->size)
	{
		printf("The queue is empty");
	}
	else {
		q->rear = (q->rear + 1) % q->size;
		q->arr[q->rear] = node;
	}
}

node* dequeueNode(queue* q)
{
	if (q->rear == q->front)
	{
		printf("The q is empty");
	}
	else {
		q->front = (q->front + 1) % q->size;
		node* temp = q->arr[q->front];
		return temp;
	}
}

bool isEmptyQueue(queue* q)
{
	return (q->front == q->rear);
}

node* buildBT() 
{
	queue* q = createQueue(50);
	int numOfNodes = 0;
	int count = 1;
	int data = INT_MIN;

	printf("How many nodes you want to enter: ");
	scanf_s("%d", &numOfNodes);

	printf("Enter the value of the root: ");
	scanf_s("%d", &data);

	node* root = createNode(data);
	enqueueNode(q, root);
	while (!isEmptyQueue(q) && count < numOfNodes)
	{
		node* likeRoot = dequeueNode(q);
		printf("Enter the left child of %d node:", likeRoot->data);
		scanf_s("%d", &data);
		node* builder = createNode(data);
		likeRoot->leftChild = builder;
		enqueueNode(q, builder);
		count++;
		if (count >= numOfNodes)
			break;

		printf("Enter the right child of %d node:", likeRoot->data);
		scanf_s("%d", &data);
		builder = createNode(data);
		likeRoot->rightChild = builder;
		enqueueNode(q, builder);
		count++;

		if (count >= numOfNodes)
			break;
	}

	return root;
}

void preOrderTraversal(node* root)
{
	if (root == NULL)
		return;

	printf("%d ", root->data);
	preOrderTraversal(root->leftChild);
	preOrderTraversal(root->rightChild);

}

int main()
{
	node* root = buildBT();
	preOrderTraversal(root);
	return 0;
}