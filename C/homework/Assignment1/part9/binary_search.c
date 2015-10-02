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

int error() {
	printf("error\n");
	//Really should return something other than 0, but our autograder can't handle that
	return 0;
}

int main(int argn, char** args) {
	if (argn != 2) {
		return error();
	}

	FILE* file = fopen(args[1], "r");

	if (file == NULL) {
		return error();
	}

	char opperation = 0;
	int value = 0;
	node* tree = new_tree_1(10);
	while ( fscanf(file, "%c %d\n", &opperation, &value) == 2) {
		if (opperation == 'i') {
			printf("inserted %d\n", insert(tree, value) - 1);
		} else if (opperation == 's') {
			int level = contains(tree, value) - 1;
			if (level == 0) {
				printf("absent\n");
			} else {
				printf("present %d\n", level);
			}
		}
	}
	return 0;
}
