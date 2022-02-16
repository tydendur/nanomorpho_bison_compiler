// Höfundur: Jon Gunnar Hannesson & Tristan Freyr Jonsson, 2017-2022

public class NanoParser {
    // Definitions of tokens:
    final static int ERROR = -1;
    final static int IF = 1001;
    final static int ELSIF = 1002;
    final static int ELSE = 1003;
    final static int NAME = 1004;
    final static int OPNAME = 1005;
    final static int LITERAL = 1006;
    final static int RETURN = 1007;
    final static int WHILE = 1008;
    final static int VAR = 1009;

    private static String advance() throws Exception {
        return NanoLexer.advance();
    }

    private static String over(int tok) throws Exception {
        return NanoLexer.over(tok);
    }

    private static String over(char tok) throws Exception {
        return NanoLexer.over(tok);
    }

    private static int getToken1() {
        return NanoLexer.getToken1();
    }

    private static String getLexeme() {
        return NanoLexer.getLexeme();
    }

    private static int getToken2() {
        return NanoLexer.getToken2();
    }

    private static int getLine() {
        return NanoLexer.getLine();
    }

    private static int getColumn() {
        return NanoLexer.getColumn();
    }

    private static void program() throws Exception {
        function();
        while (getToken1() != 0) {
            function();
        }
    }

    private static void function() throws Exception {
        over(NAME);
        over('(');
        if (getToken1() == NAME) {
            over(NAME);
            while (getToken1() == ',') {
                advance();
                over(NAME);
            }
        }
        over(')');
        over('{');
        while (getToken1() == VAR) {
            decl();
            over(';');
        }
        while (getToken1() != '}') {
            expr();
            over(';');
        }
        over('}');
    }

    private static void decl() throws Exception {
        over(VAR);
        over(NAME);
        while (getToken1() == ',') {
            advance();
            over(NAME);
        }
    }

    private static void expr() throws Exception {
        if (getToken1() == RETURN) {
            advance();
            expr();
            return;
        }
        if (getToken1() == NAME && getToken2() == '=') {
            advance();
            advance();
            expr();
            return;
        }
        binopexpr();
    }

    private static void binopexpr() throws Exception {
        smallexpr();
        while (getToken1() == OPNAME) {
            over(OPNAME);
            smallexpr();
        }
    }

    private static void smallexpr() throws Exception {
        if (getToken1() == NAME && getToken2() == '(') {
            advance();
            advance();
            if (getToken1() != ')') {
                expr();
                while (getToken1() == ',') {
                    advance();
                    expr();
                }
            }
            over(')');
            return;
        }
        if (getToken1() == NAME) {
            over(NAME);
            return;
        }
        if (getToken1() == OPNAME) {
            advance();
            smallexpr();
            return;
        }
        if (getToken1() == LITERAL) {
            advance();
            return;
        }
        if (getToken1() == '(') {
            advance();
            expr();
            over(')');
            return;
        }
        if (getToken1() == IF) {
            ifexpr();
            return;
        }
        if (getToken1() == WHILE) {
            advance();
            over('(');
            expr();
            over(')');
            body();
            return;
        }
        // ATHUGA HVERNIG ENDA SKAL IF SETNINGAR
        body();
    }

    private static void ifexpr() throws Exception {
        over(IF);
        over('(');
        expr();
        over(')');
        body();
        if (getToken1() == ELSE) {
            over(ELSE);
            // ATHUGA HVERNIG ENDA SKAL IF SETNINGAR
            if (getToken1() == IF) {
                ifexpr();
            }
            body();
        }
    }

    private static void body() throws Exception {
        over('{');
        while (getToken1() != '}') {
            expr();
            over(';');
        }
        over('}');
    }

    static public void main(String[] args) throws Exception {
        try {
            NanoLexer.startLexer(args[0]);
            program();

        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Parsing complete");
    }
}