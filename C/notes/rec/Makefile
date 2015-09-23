all: copy

copy: obj/copymain.o obj/copyutil.o
	gcc obj/copymain.o obj/copyutil.o -o copy

obj/copymain.o: copymain.c copymain.h obj
	gcc -c copymain.c -o obj/copymain.o

obj/copyutil.o: copyutil.c copyutil.h obj
	gcc -c copyutil.c -o obj/copyutil.o

obj:
	mkdir -p obj

clean:
	rm -rf obj copy

