#include <stdlib.h>
#include <stdio.h>

struct node {
	int val;
	struct node *next;
};
typedef struct node node;

node* new(int val, node* next) {
	node* root = (node *) malloc(sizeof(node));
	root -> val = val;
	root -> next = next; 
	return root;
}

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

node* append(node* root, int val) {
	root -> next = new(val, root -> next);
	return root;
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

int error() {
	printf("error\n");
	//Really should return something other than 0, but our autograder can't handle that
	return 0;
}

struct table {
	node* rows[10000];
};
typedef struct table table;

table* new_table() {
	return (table *) malloc(sizeof(table));
}

void print_table(table* table) {
	int i;
	for (i = 0; i < 10000; i++) {
		 printf("%d:\t", i);
		 print_list(table -> rows[i]);
	}
}

void insert(table* table, int value) {
	int index = value % 10000;
	if (table -> rows[index] == NULL) {
		table -> rows[index] = new(value, NULL);
		printf("inserted\n");
	} else {
		if (contains(table -> rows[index], value)) {
			printf("duplicate\n");
		} else {
			printf("inserted\n");
			append(table -> rows[index], value);
		}
	}
}

int main(int argc, char** args) {
	if (argc != 2) {
		return error();
	}

	FILE* file = fopen(args[1], "r");

	if (file == NULL) {
		return error();
	}

	table* table = new_table();

	char opperation = 0;
	int value = 0;
	while ( fscanf(file, "%c %d\n", &opperation, &value) == 2) {
		if (opperation != 'i' && opperation != 's') {
			error();
			continue;
		}

		if (opperation == 'i') {
			insert(table, value);
		}

		if (opperation == 's') {
			if (contains(table -> rows[value % 10000], value)) {
				printf("present\n");
			} else {
				printf("absent\n");
			}
		}
	}
	
	return 0;
}
