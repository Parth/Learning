#include <stdio.h>
#include <stdlib.h>

struct node {
	char* word;
	int isWord;
	node* rest;
	int rest_count;
}
typedef struct node node;

struct master {
	node* nodes;
	int nodes_count;
}
typedef struct master master;

int main(int argn, char** args) {
	return 0;
}
