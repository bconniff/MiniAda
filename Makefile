###
# CONFIGURATION
###

# change this to path to your ANTLR version 3 jar file
ANTLR=/usr/share/java/antlrworks.jar

# set this to YES if you're using windows
WINDOWS=NO

####
# RULES
####

# windows uses a semicolon instead of a colon in classpath
ifeq "$(WINDOWS)" "YES"
JARGS=-cp "$(ANTLR);."
else
JARGS=-cp "$(ANTLR):."
endif

all: MiniAdaParser.class
debug: Test.class

test: Test.class
	java $(JARGS) Test tests/*.adb

debug-MiniAdaParser.java: MiniAda.g
	java $(JARGS) org.antlr.Tool -debug MiniAda.g

debug-MiniAdaParser.class: debug-MiniAdaParser.java
	javac $(JARGS) MiniAdaParser.java

Test.class: debug-MiniAdaParser.class
	javac $(JARGS) Test.java

MiniAdaParser.java: MiniAda.g
	java $(JARGS) org.antlr.Tool MiniAda.g

MiniAdaParser.class: MiniAdaParser.java
	javac $(JARGS) MiniAdaParser.java

clean:
	rm -f *.class MiniAdaLexer.java MiniAdaParser.java MiniAda.tokens
