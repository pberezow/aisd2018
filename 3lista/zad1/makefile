JC = javac
JVM = java
PY = python
ARGS =

.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java

CLASSES = \
	Stat.java \
	Sort.java \
	Zad1.java

MAIN = Zad1

PLOTS = plots.py

default: classes

classes: $(CLASSES:.java=.class)
	jar -cmf manifest.mf main.jar ./*.class
	chmod +x ./main.jar

run: $(MAIN).class
	$(JVM) $(MAIN) $(ARGS)

plots:
	$(PY) $(PLOTS)

clean:
	$(RM) *.class *.jar
