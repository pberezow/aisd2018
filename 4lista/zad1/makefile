JC = javac
JVM = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java

CLASSES = \
	Stat.java \
	Structure.java \
	BSTNode.java \
	BSTree.java \
	RBTNode.java \
	RBTree.java \
	MyList.java \
	HMap.java \
	Zad1.java

default: classes

classes: $(CLASSES:.java=.class)
	jar -cmf manifest.mf main.jar ./*.class
	chmod +x ./main.jar
	chmod +x ./main

clean:
	$(RM) *.class *.jar
