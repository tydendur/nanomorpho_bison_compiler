%language "Java"
%define parse.error verbose
%define api.parser.class {NanoParser}
%define api.parser.extends {NanoCompiler}
%define api.parser.public

%code imports {
	import java.util.*;
	import java.io.*;
}

%code {
	String yytname(int i) {
        return yytname_[i];
    }

    void addVar(String name) {
        if (varTable.get(name) != null)
            yyerror("Variable " + name + " already exists");
        varTable.put(name, varCount++);
    }

    int findVar(String name) {
        Integer res = varTable.get(name);
        if (res == null)
            yyerror("Variable " + name + " does not exist");
        return res;
    }
}

%token<String> NAME OP1 OP2 OP3 OP4 OP5 OP6 OP7 ERROR LITERAL 
%token IF WHILE VAR RETURN ELSE ELSIF


%left RETURN '='
%left OP1
%right OP2
%left OP3
%left OP4
%left OP5
%left OP6
%left OP7
%left UNOP

%type <Vector<Object[]>> program exprs args nonemptyargs 
%type <int> varCount
%type <Object[]> expr nonemptyifrest body fundecl
%type <String> op

%%
start
	:	program {generateProgram(name, $program);}

program
	:	program fundecl		{ $1.add($fundecl); $$=$1; }
	|	fundecl				{ $$=new Vector<Object[]>(); ((Vector<Object>)($$)).add($fundecl); }
	;

varCount
	:	%empty {$$ = varCount;}
	;

fundecl
	:		{
				varCount = 0;
				varTable = new HashMap<String,Integer>();
			}
		NAME '(' pars ')' varCount '{' decls varCount exprs '}'
			{
				$$=new Object[]{$NAME,$6, $9-$6,$exprs.toArray()};
			}
	;

pars
	:	%empty
	| 	nonemptypars
	;

nonemptypars
	:	nonemptypars ',' NAME {addVar($NAME);}
	| 	NAME {addVar($NAME);}
	;

decls
	:	%empty
	| 	VAR nonemptypars ';'
	;

exprs
	:	exprs expr ';' {$1.add($2); $$ = $1;} 
	| 	expr ';'	{$$ = new Vector<Object[]>(); ((Vector<Object>)($$)).add($1);}
	;

expr
	:	RETURN expr		{$$ = new Object[] { "RETURN", $2 };}
	|	op expr %prec UNOP		{$$ = new Object[] {"CALL", $op, new Object[] {$2}};}
	|	NAME '=' expr	{$$ = new Object[] {"STORE", findVar($NAME), $3};}
	| 	expr OP1 expr	{$$ = new Object[] {"CALL", $OP1, new Object[]{$1, $3}};}
	| 	expr OP2 expr	{$$ = new Object[] {"CALL", $OP2, new Object[]{$1, $3}};}
	| 	expr OP3 expr	{$$ = new Object[] {"CALL", $OP3, new Object[]{$1, $3}};}
	| 	expr OP4 expr	{$$ = new Object[] {"CALL", $OP4, new Object[]{$1, $3}};}
	| 	expr OP5 expr	{$$ = new Object[] {"CALL", $OP5, new Object[]{$1, $3}};}
	| 	expr OP6 expr	{$$ = new Object[] {"CALL", $OP6, new Object[]{$1, $3}};}
	| 	expr OP7 expr	{$$ = new Object[] {"CALL", $OP7, new Object[]{$1, $3}};}
	| 	'(' expr ')'	{$$ = $2;}
	| 	NAME '(' args ')' {$$ = new Object[] {"CALL", $NAME, $args.toArray()};}
	| 	IF '(' expr ')' body {$$ = new Object[]{"IF1", $3, $body};}
	| 	IF '(' expr ')' body nonemptyifrest {$$ = new Object[]{"IF2", $3, $body, $nonemptyifrest};}
	| 	WHILE '(' expr ')' body {$$ = new Object[]{"WHILE", $3, $body};}
	| 	LITERAL {$$ = new Object[]{"LITERAL", $LITERAL};}
	| 	NAME {$$ = new Object[]{"FETCH", findVar($NAME)};}
	;

body
	:	'{' exprs '}' {$$ = new Object[] { "BODY", $exprs.toArray() };}
	;

args
	:	%empty		{$$ = new Vector<Object[]>();}
	| 	nonemptyargs {$$ = $nonemptyargs;}
	;

nonemptyargs
	:	nonemptyargs ',' expr {$1.add($3); $$ = $1;}
	| 	expr	{$$ = new Vector<Object[]>(); ((Vector<Object>)($$)).add($expr);}
	;

nonemptyifrest
	:	ELSE body	{$$ = $body;}
	| 	ELSIF '(' expr ')' body nonemptyifrest {$$ = new Object[]{"IF2", $expr, $body, $6};}
	| 	ELSIF '(' expr ')' body {$$ = new Object[]{"IF1", $expr, $body};}
	;

op
	:	OP1 | OP2 | OP3 | OP4 | OP5 | OP6 | OP7
	;
