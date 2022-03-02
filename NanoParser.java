import java.util.Vector;
import java.util.HashMap;
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

    private static Vector<Object[]> program() throws Exception {
        Vector<Object[]> retVal = new Vector<Object[]>();
        retVal.add(function());
        while (getToken1() != 0) {
            retVal.add(function());
        }
        return retVal;
    }

    private static Object[] function() throws Exception {
        varCount = 0;
        varTable = new HashMap<String, Integer>();
        String name = over(NAME);
        over('(');
        if (getToken1() == NAME) {
            addVar(over(NAME));
            while (getToken1() == ',') {
                advance();
                addVar(over(NAME));
            }
        }
        over(')');
        int parCount = varCount;
        over('{');
        while (getToken1() == VAR) {
            decl();
            over(';');
        }
        Vector<Object[]> exprVec = new Vector<Object[]>();

        while (getToken1() != '}') {
            exprVec.add(expr());
            over(';');
        }
        over('}');
        return new Object[] { name, parCount, varCount - parCount, exprVec.toArray() };
    }

    private static void decl() throws Exception {
        over(VAR);
        addVar(over(NAME));
        while (getToken1() == ',') {
            advance();
            addVar(over(NAME));
        }
    }

    private static Object[] expr() throws Exception {
        if (getToken1() == RETURN) {
            advance();
            return new Object[] { "RETURN", expr() };
        }
        if (getToken1() == NAME && getToken2() == '=') {
            int pos = findVar(advance());
            advance();
            return new Object[] { "STORE", pos, expr() };
        }
        return binopexpr(1);
    }

    static Object[] binopexpr(int pri) throws Exception {
        if (pri > 7) {
            return smallexpr();
        } else if (pri == 2) {
            Object[] e = binopexpr(3);
            if (getToken1() == OPNAME && priority(getLexeme()) == 2) {
                String op = advance();
                e = new Object[] { "CALL", op, new Object[] { e, binopexpr(2) } };
            }
            return e;
        } else {
            Object[] e = binopexpr(pri + 1);
            while (getToken1() == OPNAME && priority(getLexeme()) == pri) {
                String op = advance();
                e = new Object[] { "CALL", op, new Object[] { e, binopexpr(pri + 1) } };
            }
            return e;
        }
    }

    static int priority(String opname) {
        switch (opname.charAt(0)) {
            case '^':
            case '?':
            case '~':
                return 1;
            case ':':
                return 2;
            case '|':
                return 3;
            case '&':
                return 4;
            case '!':
            case '=':
            case '<':
            case '>':
                return 5;
            case '+':
            case '-':
                return 6;
            case '*':
            case '/':
            case '%':
                return 7;
            default:
                throw new Error("Invalid opname");
        }
    }

    private static Object[] smallexpr() throws Exception {
        if (getToken1() == NAME && getToken2() == '(') {
            String name = advance();
            advance();
            Vector<Object[]> args = new Vector<Object[]>();
            if (getToken1() != ')') {
                args.add(expr());
                while (getToken1() == ',') {
                    advance();
                    args.add(expr());
                }
            }
            over(')');
            return new Object[] { "CALL", name, args.toArray() };
        }
        if (getToken1() == NAME) {
            int pos = findVar(over(NAME));
            return new Object[] { "FETCH", pos };
        }
        if (getToken1() == OPNAME) {
            String name = advance();
            return new Object[] { "CALL", name, new Object[] { smallexpr() } };
        }
        if (getToken1() == LITERAL) {

            return new Object[] { "LITERAL", advance() };
        }
        if (getToken1() == '(') {
            advance();
            Object[] e = expr();
            over(')');
            return e;
        }
        if (getToken1() == IF) {
            return ifexpr();
        }
        if (getToken1() == WHILE) {
            advance();
            over('(');
            Object[] condition = expr();
            over(')');
            Object[] bod = body();
            return new Object[] { "WHILE", condition, bod };
        }
        return body();
    }

    private static Object[] ifexpr() throws Exception {
        over(IF);
        over('(');
        Object[] cond = expr();
        over(')');
        Object[] then = body();
        if (getToken1() == ELSE) {
            over(ELSE);
            if (getToken1() == IF) {
                return new Object[] { "IF2", cond, then, ifexpr() };
            }
            return new Object[] { "IF2", cond, then, body() };
        }
        return new Object[] { "IF1", cond, then };
    }

    private static Object[] body() throws Exception {
        over('{');
        Vector<Object> bod = new Vector<Object>();
        while (getToken1() != '}') {
            bod.add(expr());
            over(';');
        }
        over('}');
        return new Object[] { "BODY", bod.toArray() };
    }

    // The symbol table consists of the following two variables.
    private static int varCount;
    private static HashMap<String, Integer> varTable;

    // Adds a new variable to the symbol table.
    // Throws Error if the variable already exists.
    private static void addVar(String name) {
        if (varTable.get(name) != null)
            throw new Error("Variable " + name + " already exists");
        varTable.put(name, varCount++);
    }

    // Finds the location of an existing variable.
    // Throws Error if the variable does not exist.
    private static int findVar(String name) {
        Integer res = varTable.get(name);
        if (res == null)
            throw new Error("Variable " + name + " does not exist");
        return res;
    }

    static void generateProgram(String filename, Vector<Object[]> funs) {
        String programname = filename.substring(0, filename.indexOf('.'));
        System.out.println("\"" + programname + ".mexe\" = main in");
        System.out.println("!");
        System.out.println("{{");
        for (Object[] f : funs) {
            generateFunction(f);
        }
        System.out.println("}}");
        System.out.println("*");
        System.out.println("BASIS;");
    }

    static void generateFunction(Object[] fun) {
        System.out.println("#\"" + (String) fun[0] + "[f" + (int) fun[1] + "]\" =");
        System.out.println("[");
        System.out.println("(MakeVal null)");
        for (int i = 0; i < (int) fun[2]; i++) {
            System.out.println("(Push)");
        }
        for (Object e : (Object[]) fun[3]) {
            generateExpr((Object[]) e);
        }
        System.out.println("(Return)");
        System.out.println("];");
    }

    // All existing labels, i.e. labels the generated
    // code that we have already produced, should be
    // of form
    // _xxxx
    // where xxxx corresponds to an integer n
    // such that 0 <= n < nextLab.
    // So we should update nextLab as we generate
    // new labels.
    // The first generated label would be _0, the
    // next would be _1, and so on.
    private static int nextLab = 0;

    // Returns a new, previously unused, label.
    // Useful for control-flow expressions.
    static String newLabel() {
        return "_" + (nextLab++);
    }

    static void generateExpr(Object[] e) {
        switch ((String) e[0]) {
            case "WHILE": {
                String startlab = newLabel();
                String endlab = newLabel();
                System.out.println(startlab + ":");
                generateExpr((Object[]) e[1]);
                System.out.println("(GoFalse " + endlab + ")");
                generateExpr((Object[]) e[2]);
                System.out.println("(Go " + startlab + ")");
                System.out.println(endlab + ":");
                break;
            }
            case "LITERAL": {
                System.out.println("(MakeVal " + (String) e[1] + ")");
                break;
            }
            case "FETCH": {
                System.out.println("(Fetch " + e[1] + ")");
                break;
            }
            case "STORE": {
                generateExpr((Object[]) e[2]);
                System.out.println("(Store " + e[1] + ")");
                break;
            }
            case "CALL": {
                Object[] args = (Object[]) (e[2]);

                int n = args.length;
                generateExpr((Object[]) args[0]);
                for (int i = 1; i < n; i++) {
                    System.out.println("(Push)");
                    generateExpr((Object[]) args[i]);
                }
                System.out.println("(Call #\"" + (String) e[1] + "[f" + n + "]\" " + n + ")");
                break;
            }
            case "RETURN": {
                Object[] expr = (Object[]) e[1];

                switch ((String) expr[0]) {
                    case "CALL": {
                        Object[] args = (Object[]) (expr[2]);
                        int n = args.length;
                        generateExpr((Object[]) args[0]);
                        for (int i = 1; i < n; i++) {
                            System.out.println("(Push)");
                            generateExpr((Object[]) args[i]);
                        }
                        System.out.println("(CallR #\"" + (String) expr[1] + "[f" + n + "]\" " + n + ")");
                        break;
                    }
                    case "FETCH": {
                        System.out.println("(FetchR " + (int) expr[1] + ")");
                        break;
                    }
                    case "LITERAL": {
                        System.out.println("(MakeValR " + (String) expr[1] + ")");
                        break;
                    }
                    default: {
                        generateExpr(expr);
                        System.out.println("(Return)");
                    }
                }
            }
            case "BODY": {
                generateBody((Object[]) e[1]);
                break;
            }
            case "IF1": {
                String lab = newLabel();
                generateExpr((Object[]) e[1]);
                System.out.println("(GoFalse " + lab + ")");
                generateExpr((Object[]) e[2]);
                System.out.println(lab + ":");
                break;
            }
            case "IF2": {
                String elselab = newLabel();
                String endlab = newLabel();
                generateExpr((Object[]) e[1]);
                System.out.println("(GoFalse " + elselab + ")");
                generateExpr((Object[]) e[2]);
                System.out.println("(Go " + endlab + ")");
                System.out.println(elselab + ":");
                generateExpr((Object[]) e[3]);
                System.out.println(endlab + ":");
                break;
            }
        }
    }

    static void generateBody(Object[] bod) {
        for (Object e : bod) {
            generateExpr((Object[]) e);
        }
    }

    static public void main(String[] args) throws Exception {
        try {
            NanoLexer.startLexer(args[0]);
            Vector<Object[]> code = program();
            generateProgram(args[0], code);
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }
}