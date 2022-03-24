JAVAC = javac
JAVACFLAGS = -encoding utf8
MORPHO = java -jar morpho.jar
MORPHOC = java -jar morpho.jar -c
MORPHOCFLAGS = --encoding utf8
NANOMORPHO = java NanoCompiler
NANOPARSER = java NanoParser

NanoLexer.class NanoCompiler.class NanoParser.class: NanoLexer.java NanoCompiler.java NanoParser.java
	$(JAVAC) $(JAVACFLAGS) NanoLexer.java NanoCompiler.java NanoParser.java
NanoLexer.java: nanolexer.jflex
	java -jar jflex-full-1.8.2.jar nanolexer.jflex
NanoParser.java: NanoParser.y
	bison NanoParser.y
clean:
	rm -Rf *~ *.class NanoLexer.java *.masm *.mexe *.zip NanoParser.java
test: test.mexe
	$(MORPHO) test
test.masm: NanoParser.class test.nm
	$(NANOPARSER) test.nm > test.masm
test.mexe: test.masm
	$(MORPHOC) $(MORPHOCFLAGS) test.masm
