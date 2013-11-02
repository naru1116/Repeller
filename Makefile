.PHONY : run
JAVA_FILES := $(wildcard *.java)
CLASS_FILES := $(JAVA_FILES:%.java=%.class)
run : $(CLASS_FILES)
	java Main
%.class : %.java
	javac $<
