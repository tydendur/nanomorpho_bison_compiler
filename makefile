NanoLexer.class NanoParser.class: NanoLexer.java NanoParser.java
	javac.exe NanoLexer.java NanoParser.java
NanoLexer.java: nanolexer.jflex
	java.exe -jar jflex-full-1.8.2.jar nanolexer.jflex
zip:
	rm -Rf ../nanolexer.zip nanolexer.zip
	cd .. ;\
	zip nanolexer \
		nanolexer/jflex-full-1.8.2.jar \
		nanolexer/nanolexer.jflex \
		nanolexer/NanoParser.java \
		nanolexer/makefile \
		nanolexer/test.nm; \
	mv nanolexer.zip nanolexer
clean:
	rm -Rf *~ *.class NanoLexer.java *.masm *.mexe *.zip
test: NanoLexer.class NanoParser.class test.nm
	java.exe NanoParser test.nm