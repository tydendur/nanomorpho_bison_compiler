# jgh_lexer
Lesgreinir á la Jón Gunnar

JFLex lesgreinir fyrir NanoMorpho byggt á dæmi um NanoLisp lesgreini eftir Snorra Agnarsson.

Einnig fylgir test.nm skrá til að prófa lesgreininn. Sjá [leiðbeiningar](#build-and-run-instructions) að neðan.
Lesgreinirinn les textaskrá og skrifar út runu af tókum og lesum
(token/lexeme), eitt par í hverri línu.

Tókar eru skilgreindir með heiltölum:
* ERROR = -1
* IF = 1001
* ELSIF = 1002
* ELSE = 1003
* NAME = 1004
* OPNAME = 1005
* LITERAL = 1006
* RETURN = 1007
* WHILE = 1008

## Build and run instructions
This stand-alone scanner/lexical analyzer can be built and run using:
```
>java -jar JFlex-full-1.7.0.jar nanolexer.jflex
>javac NanoLexer.java
>java NanoLexer inputfile > outputfile
```
Also, the program 'make' can be used with the proper 'makefile':
```
>make test
```
