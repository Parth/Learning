#include <stdio.h>
#include <stdlib.h>

struct node {
	int val;
	struct node *left;
	struct node *right;
};
typedef struct node node;

node* new_tree_3(int val, node* left, node* right) {
	node* tree = (node *) malloc(sizeof(node));
	tree -> left = left;
	tree -> right = right;
	tree -> val = val;
	return tree;
}

node* new_tree_1(int val) {
	return new_tree_3(val, NULL, NULL);
}

int insert(node* tree, int val) {
	if (tree == NULL) {
		tree = new_tree_1(val);
		return 1;
	}

	if (val <= tree -> val) {
		if (tree -> left != NULL) {
			return 1 + insert(tree -> left, val);
		} else {
			tree -> left = new_tree_1(val);
			return 2;
		}
	} else {
		if (tree -> right != NULL) {
			return 1 + insert(tree -> right, val);
		} else {
			tree -> right = new_tree_1(val);
			return 2;
		}
	}
}

int contains(node* tree, int val) {
	if (tree == NULL) {
		return 0;
	}

	if (tree -> val == val) {
			return 1;
	}

	if (val <= tree -> val) {
		return 1 + contains(tree -> left, val);
	} else {
		return 1 + contains(tree -> right, val);
	}
}

void print_tree_helper(node* tree) {
	if (tree != NULL) {
		printf("%d\t", tree -> val);
		print_tree_helper(tree -> left);
		print_tree_helper(tree -> right);
	}
}

void print_tree(node* tree) {
	print_tree_helper(tree);
	printf("\n");
}

int main(int argc, char** args) {
	node* tree = NULL;

	tree = new_tree_1(10);
	
	printf("%d\n", insert(tree, 8));
	printf("%d\n", insert(tree, 12));
	printf("%d\n", insert(tree, 9));
	printf("%d\n", insert(tree, 7));
	printf("%d\n", insert(tree, 6));
	printf("%d\n", insert(tree, 5));
	printf("%d\n", insert(tree, 4));
	printf("%d\n", insert(tree, 3));
	printf("%d\n", insert(tree, 2));
	printf("%d\n", insert(tree, 1));
	print_tree(tree);
	printf("%d\n", contains(tree, 1));
	return 0;
}
