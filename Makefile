.PHONY : run
JAVA_FILES := $(wildcard *.java)
run : Main.class
	java Main
Main.class : $(JAVA_FILES)
	javac $?
