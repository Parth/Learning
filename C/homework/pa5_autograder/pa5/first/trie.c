#include <stdlib.h>
#include <stdio.h>

#include "trie.h"

node* newHead() {
	node* head = (node *) malloc(sizeof(node));

	head -> children = (node **) malloc(26 * sizeof(node *));
	int i;
	for (i = 0; i < 26; i++) {
		head -> children[i] = NULL;
	}
	head -> value = 0;
	head -> isWord = 0;
	head -> occs = 0;
	head -> prefs = 0;

	return head;
}

node* newNode(char c, int isWord) {
	node* head = (node *) malloc(sizeof(node));

	head -> children = (node **) malloc(26 * sizeof(node *));
	int i;
	for (i = 0; i < 26; i++) {
		head -> children[i] = NULL;
	}
	head -> value = c;
	head -> isWord = isWord;

	head -> isWord = 0;

	head -> occs = 0;
	head -> prefs = 0;

	return head;
}
