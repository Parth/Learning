CC = gcc 
CFLAGS = -g -Wall -Werror

default: circuit

circuit: variable.o circuit.o
	$(CC) $(CFLAGS) -o first variable.o circuit.o

circuit.o: circuit.c
	$(CC) $(CFLAGS) -c circuit.c

variable.o: variable.c variable.h
	$(CC) $(CFLAGS) -c variable.c

clean: 
	rm -rf *.out first
