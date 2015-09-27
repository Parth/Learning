#include <stdlib.h>
#include <stdio.h>

struct node {
	int x;
	struct node *next;
};

typedef struct node node;

int error() {
	printf("error\n");
	return 1;
}

int main(int argn, char** args) {
	if (argn != 2) {
		return error();
	}

	FILE* file = fopen(args[1], "r");

	if (file == NULL) {
		return error();
	}

	node *root;
	root = (node *) malloc(sizeof(node));

	root -> x = 5;
	root -> next = (node *) malloc(sizeof(node));
	root -> next -> x = 10;
	printf("%d -> %d", root -> x, root -> next -> x);
	return 0;
}
