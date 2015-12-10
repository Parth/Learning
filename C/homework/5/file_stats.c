#include <stdio.h>
#include <stdlib.h>

#include "trie.h"

node* trie;

void printHelper(node* n, int tabs) {
	if (n == NULL) {
		return;
	}

	int i;
	for (i = 0; i < tabs; i++) {
		printf("\t");
	}

	if (n -> isWord) {
		printf("!%c\n", n -> value);
	} else {
		printf("%c\n", n -> value);
	}

	for (i = 0; i < 26; i++) {
		printHelper(n -> children[i], tabs++);
	}

}

void printTrie() {
	printHelper(trie, 0);
}

int inRange(int i) {
	return (i <= 'Z' && i >= 'A') || (i <= 'z' && i >= 'a');
}

char toLower(char A) {
	if ((A >= 'A') && (A <= 'Z')) {
		return A + 32;
	} else {
		return A;
	}
}

void readDict(FILE* dictFile) {
	int next = fgetc(dictFile);
	int current = next;
	node* temp = trie;
	while(next != EOF) {
		next = fgetc(dictFile);
		if (inRange(current)) {
			current = toLower(current);
			if (temp -> children[current - 'a'] != NULL) {
				temp -> children[current - 'a'] = newNode(current, !inRange(next));
				if (!inRange(next)) {
					temp = trie;
				}
			} else {
				temp = temp -> children[current - 'a'];
				if (!inRange(next)) {
					temp -> isWord = 1;
					temp = trie;
				}
			}
		}
		current = next;
	}

	printTrie();
}

void matchStr(char* str) {

}

void printResult() {

}

int main(int argc, char** args) {
	trie = newHead();

	char dictFile[64];
	char dataFile[64];

	FILE* mappingFile;
	mappingFile = fopen(args[1], "r");
	while (fscanf(mappingFile, "%s %s", dictFile, dataFile) > 1) {
		FILE* dictFileP = fopen(dictFile, "r");
		readDict(dictFileP);
		fclose(dictFileP);
	}
	
	fclose(mappingFile);
}
