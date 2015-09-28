#include <stdlib.h>
#include <stdio.h>

struct node {
	int x;
	struct node *next;
};
typedef struct node node;

char* tostring(node* root) {
	char* return_val = "";
	return_val += "hi";
	return return_val;
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
	while ( fscanf(file, "%c %d\n", &opperation, &value) == 2) {
	}

	node *root;
	root = (node *) malloc(sizeof(node));
	printf("%s", tostring(root));

	root -> x = 5;
	root -> next = (node *) malloc(sizeof(node));
	root -> next -> x = 10;
	return 0;
}
