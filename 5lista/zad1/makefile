JC = javac
JVM = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java

CLASSES = \
	PrioQueue.java

default: classes

classes: $(CLASSES:.java=.class)
	chmod +x ./main

clean:
	$(RM) *.class
