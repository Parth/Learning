#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "trie.h"

node* trie;
int fileIndex;
FILE* currentOutput;

void printTrieHelper(node* n, int tabs) {
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
		printTrieHelper(n -> children[i], tabs+1);
	}

}

void printTrie() {
	printTrieHelper(trie, 0);
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
	int i = 0;
	while(next != EOF) {
		i++;
		next = fgetc(dictFile);
		if (inRange(current)) {
			current = toLower(current);
			if (temp -> children[current - 'a'] == NULL) {
				temp -> children[current - 'a'] = newNode(current, !inRange(next));
				if (!inRange(next)) {
					temp -> children[current - 'a'] -> isWord = 1;
					temp = trie;
				}
				temp = temp -> children[current - 'a'];
			} else {
				temp = temp -> children[current - 'a'];
				if (!inRange(next)) {
					temp -> isWord = 1;
					temp = trie;
				}
			}
		} else {
			temp = trie;
		}
		current = next;
	}
}

void increasePref(node* n) {
	if (n == NULL) {
		return;
	}

	n -> prefs++;

	int i;
	for (i = 0; i < 26; i++) {
		increasePref(n -> children[i]);
	}
}

node* find(node* n, char* str, int level) {
	if (strlen(str) == level) {
		n -> occs++;
		return n;
	}

	if (n -> children[str[level] - 'a'] != NULL) {
		return find(n -> children[str[level] - 'a'], str, level+1);
	}

	return NULL;
}

void matchStr(char* str) {
	int i;
	for (i = 0; i < strlen(str); i++) {
		str[i] = toLower(str[i]);
	}

	node* n = find(trie, str, 0);
	if (n != NULL) {
		for (i = 0; i < 26; i++) {
			increasePref(n -> children[i]);
		}
	}
}

void readData(FILE* dataFile) {
	char str[512];
	int i;
	for (i = 0; i < 512; i++) {
		str[i] = '\0';
	}

	i = 0;
	int g;
	int c;
	while ((c = fgetc(dataFile)) != EOF) {
		if (inRange(c)) {
			str[i] = c;
			i++;
		} else {
			for (g = i+1; g < 512; g++) {
				str[g] = '\0';
			}
			if (strlen(str) != 0) {
				matchStr(str);
			}
			for (g = 0; g < 512; g++) {
				str[g] = '\0';
			}
			i = 0;
		}
	}
}

void printResultHelper(node* node, char* str, int level) {
	str[level] = node -> value;
	int i;
	for (i = level+1; i < 128; i++) {
		str[i] = '\0';
	}

	if (node -> isWord) {
		fprintf(currentOutput, "%s %d %d\n", str, node -> occs, node -> prefs);
	}

	for (i = 0; i < 26; i++) {
		if (node -> children[i] != NULL) {
			printResultHelper(node -> children[i], str, level+1);
		}
	}

	for (i = level; i < 128; i++) {
		str[i] = '\0';
	}
}

void printResult() {
	char str[128];

	int i;
	for (i = 0; i < 128; i++) {
		str[i] = '\0';
	}

	for (i = 0; i < 26; i++) {
		if (trie -> children[i] != NULL) {
			printResultHelper(trie -> children[i], str, 0);
		}
	}
}

int main(int argc, char** args) {
	fileIndex = 1;
	trie = newHead();

	char dictFile[64];
	char dataFile[64];
	char outputFile[16];

	FILE* mappingFile;
	mappingFile = fopen(args[1], "r");
	while (fscanf(mappingFile, "%s %s", dictFile, dataFile) > 1) {
		FILE* dictFileP = fopen(dictFile, "r");
		readDict(dictFileP);
		fclose(dictFileP);
		FILE* dataFileP = fopen(dataFile, "r");
		readData(dataFileP);
		fclose(dataFileP);
		
		sprintf(outputFile, "out%d.txt", fileIndex);
		currentOutput = fopen(outputFile, "w+");
		printResult();
		fclose(currentOutput);
		fileIndex++;
	}
	
	fclose(mappingFile);
	return 0;
}
