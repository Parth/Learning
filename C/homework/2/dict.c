#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define true 1
#define false 0

struct node {
	char* word;
	int isWord;
	struct node** rest;
	int rest_count;
	int occ_count;
};
typedef struct node node;

node* new_node(char* word) {
	node* myNode = (node *) malloc(sizeof(node));
	myNode -> word = word;
	myNode -> rest = NULL;
	myNode -> rest_count = 0;
	myNode -> occ_count = 0;
	return myNode;
}

struct master {
	node** nodes;
	int nodes_count;
};
typedef struct master master;

master* new_master() {
	master* root = (master *) malloc(sizeof(master));
	root -> nodes_count = 0;
	root -> nodes = NULL;
	return root;
}

node* insert_helper(char* word, int level) {
	int i;

	char sub_word[level+1];
	for (i = 0; i < level; i++) {
		sub_word[i] = word[i];
	}
	sub_word[level+1] = '\0';

	node* node = new_node(sub_word);
	if (word[level] == '\0') {
		node -> isWord = true;
		node -> rest = NULL;
		node -> rest_count = 0;
		return node;
	}	

	node -> rest = (node **) malloc(sizeof(*node)*1);
	node -> rest[0] = insert_helper(word, level+1);
	node -> isWord = false;
}

void insert(master* root, char* word) {
	if (root -> nodes_count == 0) {
		root -> nodes = (node **) malloc(sizeof(*node)*1);
		root -> nodes_count = 1;
		root -> nodes[0] = insert_helper(word, 1);
	} else {
		int i;
		node* insertion_point = root -> nodes[0];
		for (i = 0; i < strlen(word); i++) {
			if (strcmp(word[i], insertion_point -> word[i]) == 0) {
				insertion_point = insertion_point -> rest[0];
			} else {
				// Between 1'st node, this sucks, our array now has to expand
				//if (
				printf("array has to grow\n");
			}
		}
	}
}

void to_string_helper(node* node, int level) {
	if (node == NULL) {
		return;
	}

	int i;
	for (i = 0; i < level; i++) {
		printf("\t");
	}

	printf("%s\n", node -> word);

	for (i=0; i < node -> rest_count; i++) {
		to_string_helper(node -> rest[i], level+1);
	}
	return;
}

void to_string(master* master) {
	printf("root\n");
	int i;
	for (i = 0; i < master -> nodes_count; i++) {
		to_string_helper(master -> nodes[i], 1);
	}
}

int main(int argn, char** args) {
	master* root = new_master();
	return 0;
}
