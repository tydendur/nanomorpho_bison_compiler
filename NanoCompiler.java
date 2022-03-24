import java.util.Vector;
import java.io.FileReader;
import java.util.HashMap;
// Höfundur: Jon Gunnar Hannesson & Tristan Freyr Jonsson, 2022

public class NanoCompiler {
    // name of program
    static String name;

    // The symbol table consists of the following two variables.
    int varCount;
    HashMap<String, Integer> varTable;

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
                System.out.println("(Fetch " + (int) e[1] + ")");
                break;
            }
            case "STORE": {
                generateExpr((Object[]) e[2]);
                System.out.println("(Store " + (int) e[1] + ")");
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
            NanoLexer lexer = new NanoLexer(new FileReader(args[0]));
            NanoParser parser = new NanoParser(lexer);
            name = args[0];
            parser.parse();
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }
}