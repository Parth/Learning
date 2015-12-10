#ifndef TRIE_H
#define TRIE_H

struct node { 
	struct node** children;
	char value;
	int isWord;
};
typedef struct node node;

node* newHead();
node* newNode(char value, int isWord);

#endif
