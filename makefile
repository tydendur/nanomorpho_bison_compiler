JAVAC = javac
JAVACFLAGS = -encoding utf8
MORPHO = java -jar morpho.jar
MORPHOC = java -jar morpho.jar -c
MORPHOCFLAGS = --encoding utf8
NANOMORPHO = java NanoParser

NanoLexer.class NanoParser.class: NanoLexer.java NanoParser.java
	$(JAVAC) $(JAVACFLAGS) NanoLexer.java NanoParser.java
NanoLexer.java: nanolexer.jflex
	java -jar jflex-full-1.8.2.jar nanolexer.jflex
clean:
	rm -Rf *~ *.class NanoLexer.java *.masm *.mexe *.zip
test: test.mexe
	$(MORPHO) test
test.masm: test.nm NanoParser.class
	$(NANOMORPHO) test.nm > test.masm
test.mexe: test.masm
	$(MORPHOC) $(MORPHOCFLAGS) test.masm
