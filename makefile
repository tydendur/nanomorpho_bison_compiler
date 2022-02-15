NanoLexer.class NanoParser.class: NanoLexer.java NanoParser.java
	javac.exe NanoLexer.java NanoParser.java
NanoLexer.java: nanolexer.jflex
	java.exe -jar jflex-full-1.8.2.jar nanolexer.jflex
clean:
	rm -Rf *~ *.class NanoLexer.java *.masm *.mexe *.zip
test: NanoLexer.class NanoParser.class test.nm
	java.exe NanoParser test.nm