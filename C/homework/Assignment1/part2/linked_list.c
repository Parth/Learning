#include <stdlib.h>
#include <stdio.h>

struct node {
	int x;
	struct node *next;
};

typedef struct node node;

int main(int argn, char** args) {
	node *root;
	root = (node *) malloc(sizeof(node));

	root -> x = 5;
	root -> next = (node *) malloc(sizeof(node));
	root -> next -> x = 10;
	printf("%d -> %d", root -> x, root -> next -> x);
	return 0;
}
