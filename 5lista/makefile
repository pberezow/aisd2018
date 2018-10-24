
DIRS = zad1 zad2 zad3 zad4

all: do

do:
	for dir in $(DIRS); do \
		cd $$dir; \
		make; \
		cd ..; \
	done

clean: $(DIRS)
	for dir in $(DIRS); do \
		cd $$dir; \
		make clean; \
		cd ..; \
	done
