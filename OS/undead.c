// clang -m32 -g -S undead.c
// Parth Mehrotra
// Confirmed works on vi.cs.rutgers.edu

#include <signal.h>
#include <stdio.h>
#include <stdlib.h>

void segment_fault_handler(int signum) {
	printf("I am slain!\n");

	void* ptr = (void*) &signum;
	// find it 0x4c-0x10
	ptr += 60;

	//increment by length of bad inst
	// 0x6
	*(int *)ptr += 6;
}


int main() {
	int r2 = 0;

	signal(SIGSEGV, segment_fault_handler);

	r2 = *( (int *) 0 );
	
	printf("I live again!\n");

	return 0;
}
