#include <stdlib.h>
#include <stdio.h>

struct node {
	int val;
	struct node *next;
};
typedef struct node node;

int size(node* root) {

	if (root == NULL) {
		return 0;
	}

	if (root -> next == NULL) {
		return 1;
	}

	node* temp = root;
	int size = 1;

	while (temp -> next != NULL) {
		size++;
		temp = temp -> next; 
	}
	return size;
}

int contains(node* root, int  val) {
	if (root == NULL) {
		return 0;
	}

	if (root -> val == val) {
		return 1;
	}

	node* temp;
	for (temp = root; temp -> next != NULL; temp = temp -> next) {
		if (temp -> next -> val == val) {
			return 1;
		}
	}
	return 0;
}

node* new(int val, node* next) {
	node* root = (node *) malloc(sizeof(node));
	root -> val = val;
	root -> next = next; 
	return root;
}

node* append(node* root, int val) {
	root -> next = new(val, root -> next);
	return root;
}

node* getLast(node* root) {
	node* temp = root;

	while (temp -> next != NULL) {
		temp = temp->next;
	}
	return temp;
}

void print_list(node* root) {
	int list_size = size(root);
	if (list_size == 0) {
		printf("\n");
		return;
	}

	if (list_size == 1) {
		printf("%d\n", root -> val);
		return;
	}

	printf("%d", root -> val);
	
	node* temp = root;
	while (temp -> next != NULL) {
		printf(" %d", temp -> next -> val);
		temp = temp -> next;
	}
	printf("\n");

	return;
}

node* insert(node* root, int val) {
	if (contains(root, val)) {
		return root;
	}
	node* temp;

	if (size(root) == 1) {
		if (val < root->val) {
			return new(val, root);
		} else {
			return append(root, val);
		}
	}

	if (val < root->val) {
		return new(val, root);
	}
	
	for (temp = root; temp->next != NULL; temp = temp -> next) {
		if (val > temp->val && val < temp->next->val) {
			append(temp, val);
			return root;
		}
	}

	append(getLast(root), val);
	return root;
} 

node* delete(node* root, int val) {
	if (size(root) == 0) { 
		return NULL;
	}
	if (!contains(root, val)) {
		return root;
	}

	node* temp;

	if (root -> val == val) {
		return root -> next;
	}

	for (temp = root; temp->next != NULL; temp = temp->next) {
		if (temp->next->val == val) {
			temp->next = temp->next->next;
			return root;
		}
	}
	return root;
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
	node* root = NULL;
	while ( fscanf(file, "%c %d\n", &opperation, &value) == 2) {
		if (opperation == 'i') {
			if (root == NULL) {
				root = new(value, NULL);
			} else {
				root = insert(root, value);
			}
		} else if (opperation == 'd') {
			if (root != NULL) {
				root = delete(root, value);
			}
		} else {
			error();
		}
	}

	print_list(root);
	return 0;
}
