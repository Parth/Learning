CC = gcc 
CFLAGS = -g -Wall -Werror 

default: file_stats

file_stats: trie.o file_stats.o
	$(CC) $(CFLAGS) -o first trie.o file_stats.o

tests: trie.o tests.o
	$(CC) $(CFLAGS) -o test trie.o tests.o

file_stats.o: file_stats.c
	$(CC) $(CFLAGS) -c file_stats.c

tests.o: tests.c
	$(CC) $(CFLAGS) -c tests.c

trie.o: trie.c trie.h
	$(CC) $(CFLAGS) -c trie.c

clean: 
	rm -rf *.o first
