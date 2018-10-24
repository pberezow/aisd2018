JC = javac
JVM = java
ARGS =

.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java

CLASSES = \
	Select.java \
	Stat.java \
	Zad2.java

MAIN = Zad2

default: classes

classes: $(CLASSES:.java=.class)
	jar -cmf manifest.mf main.jar ./*.class
	chmod +x ./main.jar

run: $(MAIN).class
	$(JVM) $(MAIN) $(ARGS)

clean:
	$(RM) *.class *.jar
