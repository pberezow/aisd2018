CC = gcc
CFLAGS = -std=c99 -pedantic -Wall

all: queue linkedList cyclicList

queue: queue.c
	$(CC) $(CFLAGS) -o queue.o queue.c

linkedList: linkedList.c
	$(CC) $(CFLAGS) -o linkedList.o linkedList.c

cyclicList: cyclicList.c
	$(CC) $(CFLAGS) -o cyclicList.o cyclicList.c

clean:
	rm *.o
