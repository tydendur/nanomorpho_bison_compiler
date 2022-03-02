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

import java.io.*;

%%

%public
%class NanoLexer
%unicode
%byaccj
%line
%column

%{

private static String lexeme1;
private static String lexeme2;
private static int token1;
private static int token2;
private static NanoLexer lexer;
private static int line1, column1, line2, column2;

public static void startLexer( String filename ) throws Exception
{
	startLexer(new FileReader(filename));
}

public static void startLexer( Reader r ) throws Exception
{
	lexer = new NanoLexer(r);
	token2 = lexer.yylex();
	line2 = lexer.yyline;
	column2 = lexer.yycolumn;
	advance();
}

public static String advance() throws Exception
{
	String res = lexeme1;
	token1 = token2;
	lexeme1 = lexeme2;
	line1 = line2;
	column1 = column2;
	if( token2==0 ) return res;
	token2 = lexer.yylex();
	line2 = lexer.yyline;
	column2 = lexer.yycolumn;
	return res;
}

public static int getLine()
{
	return line1+1;
}

public static int getColumn()
{
	return column1+1;
}

public static int getToken1()
{
	return token1;
}

public static int getToken2()
{
	return token2;
}

public static String getLexeme()
{
	return lexeme1;
}

private static void expected( int tok )
{
	expected(tokname(tok));
}

private static void expected( char tok )
{
	expected("'"+tok+"'");
}

public static void expected( String tok )
{
	throw new Error("Expected "+tok+", found '"+lexeme1+"' near line "+(line1+1)+", column "+(column1+1));
}

private static String tokname( int tok )
{
	if( tok<1000 ) return ""+(char)tok;
	switch( tok )
	{
	case NanoCompiler.IF:
		return "if";
	case NanoCompiler.ELSE:
		return "else";
	case NanoCompiler.ELSIF:
		return "elsif";
	case NanoCompiler.WHILE:
		return "while";
	case NanoCompiler.VAR:
		return "var";
	case NanoCompiler.RETURN:
		return "return";
	case NanoCompiler.NAME:
		return "name";
	case NanoCompiler.OPNAME:
		return "operation";
	case NanoCompiler.LITERAL:
		return "literal";
	}
	throw new Error();
}

public static String over( int tok ) throws Exception
{
	if( token1!=tok ) expected(tok);
	String res = lexeme1;
	advance();
	return res;
}

public static String over( char tok ) throws Exception
{
	if( token1!=tok ) expected(tok);
	String res = lexeme1;
	advance();
	return res;
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
	lexeme2 = yytext();
	return yycharat(0);
}

{_STRING} | {_FLOAT} | {_CHAR} | {_INT} | null | true | false {
	lexeme2 = yytext();
	return NanoCompiler.LITERAL;
}

"if" {
	lexeme2 = yytext();
	return NanoCompiler.IF;
}

"elsif" {
	lexeme2 = yytext();
	return NanoCompiler.ELSIF;
}

"else" {
	lexeme2 = yytext();
	return NanoCompiler.ELSE;
}
"while" {
	lexeme2 = yytext();
	return NanoCompiler.WHILE;
}
"return" {
	lexeme2 = yytext();
	return NanoCompiler.RETURN;
}
"var" {
	lexeme2 = yytext();
	return NanoCompiler.VAR;
}

{_NAME} {
	lexeme2 = yytext();
	return NanoCompiler.NAME;
}

{_OPNAME} {
	lexeme2 = yytext();
	return NanoCompiler.OPNAME;
}

  /* Comments start with a pound sign. */
"#".*$ {
}

[ \t\r\n\f] {
}

. {
	lexeme2 = yytext();
	return NanoCompiler.ERROR;
}
