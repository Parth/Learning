CC = gcc 
CFLAGS = -g -Wall -Werror -O3

default: first

first: trie.o file_stats.o
	$(CC) $(CFLAGS) -o first trie.o file_stats.o

file_stats.o: file_stats.c
	$(CC) $(CFLAGS) -c file_stats.c

trie.o: trie.c trie.h
	$(CC) $(CFLAGS) -c trie.c

clean: 
	rm -rf *.o first
