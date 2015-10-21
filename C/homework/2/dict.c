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

	node* myNode = (node *) malloc(sizeof(node));
	myNode -> word = (char *) malloc(sizeof(char)*level+1);
	int i;
	for (i = 0; i < level; i++) {
		myNode -> word[i] = word[i];
	}
	myNode -> word[level] = '\0';

	if (word[level] == '\0') {
		myNode -> isWord = true;
		myNode -> rest = NULL;
		myNode -> rest_count = 0;
		return myNode;
	}	

	myNode -> rest = (node **) malloc(sizeof(node *));
	myNode -> isWord = false;
	myNode -> rest_count = 1;
	myNode -> rest[0] = insert_helper(word, level+1);
	return myNode;
}

void insert(master* root, char* word) {
	printf("word: %s\n", word);
	if (root -> nodes_count == 0) {
		root -> nodes = (node **) malloc(sizeof(node *)*1);
		root -> nodes_count = 1;
		root -> nodes[0] = insert_helper(word, 1);
		return;
	} else {
		int i, j;
		int match = false;
		node* insertion_point = NULL;
		j = 0;
		for (i = 0; i < root -> nodes_count; i++) {
			if (word[j] == root -> nodes[i] -> word[j]) {
				insertion_point = root -> nodes[i];
				match = true;
			}
		}
		if (!match) {
			for (i = 0; i < root -> nodes_count; i++) {
				if (word[j] < root -> nodes[i] -> word[j]) {
					node** new = (node **) malloc(sizeof(node*) * root -> nodes_count + 1);
					int k;
					for (k = 0; k < root -> nodes_count + 1; k++) {
						if (k < i) {
							new[k] = (node *) malloc(sizeof(node));
							new[k] = root -> nodes[k];
							free(root -> nodes[k]);
						} else if (k == i) {
							new[k] = (node *) malloc(sizeof(node));
							new[k] = insert_helper(word, 1);
						} else {
							new[k] = (node *) malloc(sizeof(node));
							new[k] = root -> nodes[k-1];
							free(root -> nodes[k-1]);
						}
					}
					free(root -> nodes);
					root -> nodes = new;
					root -> nodes_count++;
					return;
				}
			}
			node** new = (node **) malloc(sizeof(node*) * root -> nodes_count + 1);
			int k;
			for (k = 0; k < root -> nodes_count + 1; k++) {
				new[k] = (node *) malloc(sizeof(node));
				new[k] = root -> nodes[k];
				free(root -> nodes[k]);
			}
			free(root -> nodes);
			new[k+1] = insert_helper(word, 1);
			root -> nodes = new;
		}
		//
		match = false;
		for (j = 1; j < strlen(word); j++) {
			for (i = 0; i < insertion_point -> rest_count; i++) {
				if (word[j] == insertion_point -> rest[i] -> word[j]) {
					insertion_point = insertion_point -> rest[i];
					match = true;
				}
			}
			if (!match) {
				for (i = 0; i < insertion_point -> rest_count; i++) {
					if (word[j] < insertion_point -> rest[i] -> word[j]) {
						node** new = (node **) malloc(sizeof(node*) * insertion_point -> rest_count + 1);
						int k;
						for (k = 0; k < insertion_point -> rest_count + 1; k++) {
							if (k < i) {
								new[k] = (node *) malloc(sizeof(node));
								new[k] = insertion_point -> rest[k];
								free(insertion_point -> rest[k]);
							} else if (k == i) {
								new[k] = (node *) malloc(sizeof(node));
								new[k] = insert_helper(word, 1);
							} else {
								new[k] = (node *) malloc(sizeof(node));
								new[k] = insertion_point -> rest[k-1];
								free(insertion_point -> rest[k-1]);
							}
						}
						free(insertion_point -> rest);
						insertion_point -> rest = new;
						root -> nodes_count++;
						return;
					}
				}
				node** new = (node **) malloc(sizeof(node*) * insertion_point -> rest_count + 1);
				int k;
				for (k = 0; k < insertion_point -> rest_count + 1; k++) {
					new[k] = (node *) malloc(sizeof(node));
					new[k] = insertion_point -> rest[k];
					free(insertion_point -> rest[k]);
				}
				free(insertion_point -> rest);
				new[k+1] = insert_helper(word, 1);
				insertion_point -> rest = new;
			}
		}
	}
	root -> nodes_count++;
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
	insert(root, "thank god");
	insert(root, "please");
	to_string(root);
	return 0;
}
