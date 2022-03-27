/**
	JFlex scanner for NanoMorpho based on a scanner example for NanoLisp.
	Author: Jon Gunnar Hannesson, 2022

	This stand-alone scanner/lexical analyzer can be built and run using:
		java -jar JFlex-full-1.7.0.jar nanolexer.jflex
		javac NanoLexer.java
		java NanoLexer inputfile > outputfile
	Also, the program 'make' can be used with the proper 'makefile':
		make test
 */

%%

%public
%class NanoLexer
%implements NanoParser.Lexer
%unicode
%byaccj
%line
%column

%{

private static String yylval;

public String getLVal()
	{
		return yylval;
	}

public void yyerror( String error )
	{
		System.out.println("Error:  "+error);
		System.out.println("Token:  "+yylval + " near line "+(yyline+1)+", column "+(yycolumn+1));
		System.exit(1);
	}
%}

  /* Reglulegar skilgreiningar */

  /* Regular definitions */

_DIGIT=[0-9]
_FLOAT={_DIGIT}+\.{_DIGIT}+([eE][+-]?{_DIGIT}+)?
_INT={_DIGIT}+
_STRING=\"([^\"\\]|\\b|\\t|\\n|\\f|\\r|\\\"|\\\'|\\\\|(\\[0-3][0-7][0-7])|\\[0-7][0-7]|\\[0-7])*\"
_CHAR=\'([^\'\\]|\\b|\\t|\\n|\\f|\\r|\\\"|\\\'|\\\\|(\\[0-3][0-7][0-7])|(\\[0-7][0-7])|(\\[0-7]))\'
_DELIM=[(){};,=]
_NAME=[:jletter:]([:jletter:]|{_DIGIT})*
_OPNAME=[\+\-*/!%&=><\:\^\~&|?]+

%%

  /* Lesgreiningarreglur */
  /* Scanning rules */

{_DELIM} {
	yylval = yytext();
	return yycharat(0);
}

{_STRING} | {_FLOAT} | {_CHAR} | {_INT} | null | true | false {
	yylval = yytext();
	return LITERAL;
}

"if" {
	yylval = yytext();
	return IF;
}

"elsif" {
	yylval = yytext();
	return ELSIF;
}

"else" {
	yylval = yytext();
	return ELSE;
}
"while" {
	yylval = yytext();
	return WHILE;
}
"return" {
	yylval = yytext();
	return RETURN;
}
"var" {
	yylval = yytext();
	return VAR;
}

{_NAME} {
	yylval = yytext();
	return NAME;
}

{_OPNAME} {
	yylval = yytext();
	// TODO spyrja snorra hvad er i gangi herwhat
	switch( yytext().charAt(0) )
	{
	case '^':
	case '?':
	case '~':
		return OP1;
	case ':':
		return OP2;
	case '|':
		return OP3;
	case '&':
		return OP4;
	case '!':
	case '=':
	case '<':
	case '>':
		return OP5;
	case '+':
	case '-':
		return OP6;
	case '*':
	case '/':
	case '%':
		return OP7;
	default:
		throw new Error("Invalid operation name");
	}
}
  /* Comments start with a pound sign. */
"#".*$ {
}

[ \t\r\n\f] {
}

. {
	yylval = yytext();
	return ERROR;
}
