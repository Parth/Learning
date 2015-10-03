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

	if (val <= tree -> val) { if (tree -> left != NULL) {
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
		int r = contains(tree -> left, val);
		if (r == 0) {
			return 0;
		} else {
			return 1 + r;
		}
	} else {
		int r = contains(tree -> right, val);
		if (r == 0) {
			return 0;
		} else {
			return 1+r;
		}
	}
}


node* min(node* tree) {
    node* pointer = tree;
 
    while (pointer->left != NULL) {
        pointer = pointer->left;
	}
 
    return pointer;
}

node* delete(node* root, int val) {
    if (root == NULL) {
		return root;
	}
 
    if (val < root->val) {
        root->left = delete(root->left, val);
	} else if (val > root->val) {
        root->right = delete(root->right, val);
	} else {
        if (root->left == NULL) {
            node *temp = root->right;
            free(root);
            return temp;
        }
        else if (root->right == NULL) {
            node *temp = root->left;
            free(root);
            return temp;
        }
 
        node* temp = min(root->right);
        root->val = temp->val;
        root->right = delete(root->right, temp->val);
    }
    return root;
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

int main() {
	node* tree = new_tree_1(1);
	printf("i %d\n", insert(tree, 2));
	printf("i %d\n", insert(tree, 3));
	print_tree(tree);
	delete(tree, 3);
	print_tree(tree);
	delete(tree, 1);
	print_tree(tree);
	return 0;
}
int main2(int argn, char** args) {
	if (argn != 2) {
		return error();
	}

	FILE* file = fopen(args[1], "r");

	if (file == NULL) {
		return error();
	}

	char opperation = 0;
	int value = 0;
	node* tree = NULL;
	while (fscanf(file, "%c %d\n", &opperation, &value) == 2) {
		if (opperation == 'i') {
			if (tree != NULL) {
				if (contains(tree, value)) {
					printf("duplicate\n");
				} else {
					printf("inserted %d\n", insert(tree, value));
				}
			} else {
				tree = new_tree_1(value);
				printf("inserted 1\n");
			}
		} else if (opperation == 'd') {
			if (contains(tree, value) == 0) {
				printf("fail\n");
				continue;
			} else {
				delete(tree, value);
				printf("success\n");
			}
		} else if (opperation == 's') {
			int level = contains(tree, value);
			if (level == 0) {
				printf("absent\n");
			} else {
				printf("present %d\n", level);
			}
		}
	}
	return 0;
}
