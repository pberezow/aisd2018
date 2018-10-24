JC = javac
JVM = java
ARGS =

.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java

CLASSES = \
	BinarySearch.java \
	Stat.java \
	Zad3.java

MAIN = Zad3

default: classes

classes: $(CLASSES:.java=.class)
	jar -cmf manifest.mf main.jar ./*.class
	chmod +x ./main.jar

run: $(MAIN).class
	$(JVM) $(MAIN) $(ARGS)

clean:
	$(RM) *.class *.jar
