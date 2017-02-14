// clang -m32 -g -S undead.c
// Parth Mehrotra
// Confirmed works on vi.cs.rutgers.edu

#include <signal.h>
#include <stdio.h>
#include <stdlib.h>

void segment_fault_handler(int signum) {
	printf("I am slain!\n");

<<<<<<< HEAD
	//Use the signnum to construct a pointer to flag on stored stack
	//Increment pointer down to the stored PC
	//Increment value at pointer by length of bad instruction

        void* ptr = (void*) &signum;
        ptr += 0x4c-0x10;
        *(int *)ptr += 0x6;
	
=======
	void* ptr = (void*) &signum;
	// find it 0x4c-0x10
	ptr += 60;

	//increment by length of bad inst
	// 0x6
	*(int *)ptr += 6;
>>>>>>> c26f10f3a26edf52972b5a511172dc737df828e8
}


int main() {
	int r2 = 0;

	signal(SIGSEGV, segment_fault_handler);

	r2 = *( (int *) 0 );
	
	printf("I live again!\n");

	return 0;
}
