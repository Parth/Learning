CC = gcc 
CFLAGS = -g -Wall -Werror

default: circuit

circuit: variable.o circuit.o
	$(CC) $(CFLAGS) -o first variable.o circuit.o

tests: variable.o tests.o
	$(CC) $(CFLAGS) -o test variable.o tests.o

circuit.o: circuit.c
	$(CC) $(CFLAGS) -c circuit.c

tests.o: tests.c
	$(CC) $(CFLAGS) -c tests.c

variable.o: variable.c variable.h
	$(CC) $(CFLAGS) -c variable.c

clean: 
	rm -rf *.out first
