NanoMorphoLexer.class NanoMorphoParser.class: NanoMorphoLexer.java NanoMorphoParser.java
	javac.exe NanoMorphoLexer.java NanoMorphoParser.java
NanoMorphoLexer.java: nanomorpholexer.jflex
	java.exe -jar jflex-full-1.8.2.jar nanomorpholexer.jflex
zip:
	rm -Rf ../nanomorpholexer.zip nanomorpholexer.zip
	cd .. ;\
	zip nanomorpholexer \
		nanomorpholexer/jflex-full-1.8.2.jar \
		nanomorpholexer/nanomorpholexer.jflex \
		nanomorpholexer/NanoMorphoParser.java \
		nanomorpholexer/makefile \
		nanomorpholexer/test.nm; \
	mv nanomorpholexer.zip nanomorpholexer
clean:
	rm -Rf *~ *.class NanoMorphoLexer.java *.masm *.mexe *.zip
test: NanoMorphoLexer.class NanoMorphoParser.class test.nm
	java.exe NanoMorphoParser test.nm