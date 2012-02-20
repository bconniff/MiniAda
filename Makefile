all: MiniAdaParser.class Test.class

test: Test.class
	java Test tests/*.adb

MiniAdaParser.class: MiniAdaParser.java
	javac MiniAdaParser.java

MiniAdaParser.java: MiniAda.g
	antlr3 -debug MiniAda.g

Test.class: MiniAdaParser.class
	javac Test.java

clean:
	rm -f *.class MiniAdaLexer.java MiniAdaParser.java MiniAda.tokens
