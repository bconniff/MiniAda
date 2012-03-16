###
# CONFIGURATION
###

# change this to path to your ANTLR version 3 jar file
ANTLR=/usr/share/java/antlrworks.jar

####
# RULES
####

JARGS=-cp ".:$(ANTLR)"

all: MiniAdaParser.class
debug: Test.class TestAST.class
clean: clean-main clean-trees

test: Test.class
	java $(JARGS) Test tests/*.adb

test-ast: TestAST.class
	java $(JARGS) TestAST tests/*.adb

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

clean-main:
	rm -f *.class MiniAdaLexer.java MiniAdaParser.java MiniAda.tokens

clean-trees:
	rm -f trees/*.class
