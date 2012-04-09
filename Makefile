###
# CONFIGURATION
###

# change this to path to your ANTLR version 3 jar file
ANTLR=/usr/share/java/antlrworks.jar

####
# RULES
####

JARGS=-cp ".:$(ANTLR)"

all: Main.class
debug: Test.class TestAST.class

clean:
	rm -f MiniAdaLexer.java MiniAdaParser.java MiniAda.tokens
	rm -f *.class
	rm -f trees/*.class
	rm -f symbols/*.class
	rm -f symbols/types/*.class
	rm -f symbols/attributes/*.class
	rm -f utils/*.class
	rm -f visitors/*.class

test: Test.class
	java $(JARGS) Test tests/*.adb

test-ast: TestAST.class
	java $(JARGS) TestAST tests/*.adb

Main.class: MiniAdaParser.class
	javac $(JARGS) Main.java

debug-MiniAdaParser.java: MiniAda.g
	java $(JARGS) org.antlr.Tool -debug MiniAda.g

debug-MiniAdaParser.class: debug-MiniAdaParser.java
	javac $(JARGS) MiniAdaParser.java

Test.class: debug-MiniAdaParser.class
	javac $(JARGS) Test.java

TestAST.class: debug-MiniAdaParser.class
	javac $(JARGS) TestAST.java

MiniAdaParser.java: MiniAda.g
	java $(JARGS) org.antlr.Tool MiniAda.g

MiniAdaParser.class: MiniAdaParser.java
	javac $(JARGS) MiniAdaParser.java
