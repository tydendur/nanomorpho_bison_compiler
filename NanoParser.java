/* A Bison parser, made by GNU Bison 3.5.1.  */

/* Skeleton implementation for Bison LALR(1) parsers in Java

   Copyright (C) 2007-2015, 2018-2020 Free Software Foundation, Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */




/* "%code imports" blocks.  */
/* "NanoParser.y":7  */

	import java.util.*;
	import java.io.*;

/* "NanoParser.java":43  */

/**
 * A Bison parser, automatically generated from <tt>NanoParser.y</tt>.
 *
 * @author LALR (1) parser skeleton written by Paolo Bonzini.
 */
public class NanoParser extends NanoCompiler
{
    /** Version number for the Bison executable that generated this parser.  */
  public static final String bisonVersion = "3.5.1";

  /** Name of the skeleton that generated this parser.  */
  public static final String bisonSkeleton = "lalr1.java";


  /**
   * True if verbose error messages are enabled.
   */
  private boolean yyErrorVerbose = true;

  /**
   * Whether verbose error messages are enabled.
   */
  public final boolean getErrorVerbose() { return yyErrorVerbose; }

  /**
   * Set the verbosity of error messages.
   * @param verbose True to request verbose error messages.
   */
  public final void setErrorVerbose(boolean verbose)
  { yyErrorVerbose = verbose; }






  /**
   * Communication interface between the scanner and the Bison-generated
   * parser <tt>NanoParser</tt>.
   */
  public interface Lexer {
    /** Token returned by the scanner to signal the end of its input.  */
    public static final int EOF = 0;

/* Tokens.  */
    /** Token number,to be returned by the scanner.  */
    static final int NAME = 258;
    /** Token number,to be returned by the scanner.  */
    static final int OP1 = 259;
    /** Token number,to be returned by the scanner.  */
    static final int OP2 = 260;
    /** Token number,to be returned by the scanner.  */
    static final int OP3 = 261;
    /** Token number,to be returned by the scanner.  */
    static final int OP4 = 262;
    /** Token number,to be returned by the scanner.  */
    static final int OP5 = 263;
    /** Token number,to be returned by the scanner.  */
    static final int OP6 = 264;
    /** Token number,to be returned by the scanner.  */
    static final int OP7 = 265;
    /** Token number,to be returned by the scanner.  */
    static final int ERROR = 266;
    /** Token number,to be returned by the scanner.  */
    static final int LITERAL = 267;
    /** Token number,to be returned by the scanner.  */
    static final int IF = 268;
    /** Token number,to be returned by the scanner.  */
    static final int WHILE = 269;
    /** Token number,to be returned by the scanner.  */
    static final int VAR = 270;
    /** Token number,to be returned by the scanner.  */
    static final int RETURN = 271;
    /** Token number,to be returned by the scanner.  */
    static final int ELSE = 272;
    /** Token number,to be returned by the scanner.  */
    static final int ELSIF = 273;
    /** Token number,to be returned by the scanner.  */
    static final int UNOP = 274;


    

    /**
     * Method to retrieve the semantic value of the last scanned token.
     * @return the semantic value of the last scanned token.
     */
    Object getLVal ();

    /**
     * Entry point for the scanner.  Returns the token identifier corresponding
     * to the next token and prepares to return the semantic value
     * of the token.
     * @return the token identifier corresponding to the next token.
     */
    int yylex () throws java.io.IOException;

    /**
     * Entry point for error reporting.  Emits an error
     * in a user-defined way.
     *
     * 
     * @param msg The string for the error message.
     */
     void yyerror (String msg);
  }

/**
   * The object doing lexical analysis for us.
   */
  private Lexer yylexer;

  



  /**
   * Instantiates the Bison-generated parser.
   * @param yylexer The scanner that will supply tokens to the parser.
   */
  public NanoParser (Lexer yylexer) 
  {
    
    this.yylexer = yylexer;
    
  }



  /**
   * Print an error message via the lexer.
   *
   * @param msg The error message.
   */
  public final void yyerror (String msg)
  {
    yylexer.yyerror (msg);
  }



  private final class YYStack {
    private int[] stateStack = new int[16];
    
    private Object[] valueStack = new Object[16];

    public int size = 16;
    public int height = -1;

    public final void push (int state, Object value                            ) {
      height++;
      if (size == height)
        {
          int[] newStateStack = new int[size * 2];
          System.arraycopy (stateStack, 0, newStateStack, 0, height);
          stateStack = newStateStack;
          

          Object[] newValueStack = new Object[size * 2];
          System.arraycopy (valueStack, 0, newValueStack, 0, height);
          valueStack = newValueStack;

          size *= 2;
        }

      stateStack[height] = state;
      
      valueStack[height] = value;
    }

    public final void pop () {
      pop (1);
    }

    public final void pop (int num) {
      // Avoid memory leaks... garbage collection is a white lie!
      if (0 < num) {
        java.util.Arrays.fill (valueStack, height - num + 1, height + 1, null);
        
      }
      height -= num;
    }

    public final int stateAt (int i) {
      return stateStack[height - i];
    }

    public final Object valueAt (int i) {
      return valueStack[height - i];
    }

    // Print the state stack on the debug stream.
    public void print (java.io.PrintStream out) {
      out.print ("Stack now");

      for (int i = 0; i <= height; i++)
        {
          out.print (' ');
          out.print (stateStack[i]);
        }
      out.println ();
    }
  }

  /**
   * Returned by a Bison action in order to stop the parsing process and
   * return success (<tt>true</tt>).
   */
  public static final int YYACCEPT = 0;

  /**
   * Returned by a Bison action in order to stop the parsing process and
   * return failure (<tt>false</tt>).
   */
  public static final int YYABORT = 1;



  /**
   * Returned by a Bison action in order to start error recovery without
   * printing an error message.
   */
  public static final int YYERROR = 2;

  /**
   * Internal return codes that are not supported for user semantic
   * actions.
   */
  private static final int YYERRLAB = 3;
  private static final int YYNEWSTATE = 4;
  private static final int YYDEFAULT = 5;
  private static final int YYREDUCE = 6;
  private static final int YYERRLAB1 = 7;
  private static final int YYRETURN = 8;


  private int yyerrstatus_ = 0;


  /**
   * Whether error recovery is being done.  In this state, the parser
   * reads token until it reaches a known state, and then restarts normal
   * operation.
   */
  public final boolean recovering ()
  {
    return yyerrstatus_ == 0;
  }

  /** Compute post-reduction state.
   * @param yystate   the current state
   * @param yysym     the nonterminal to push on the stack
   */
  private int yyLRGotoState (int yystate, int yysym)
  {
    int yyr = yypgoto_[yysym - yyntokens_] + yystate;
    if (0 <= yyr && yyr <= yylast_ && yycheck_[yyr] == yystate)
      return yytable_[yyr];
    else
      return yydefgoto_[yysym - yyntokens_];
  }

  private int yyaction (int yyn, YYStack yystack, int yylen) 
  {
    /* If YYLEN is nonzero, implement the default value of the action:
       '$$ = $1'.  Otherwise, use the top of the stack.

       Otherwise, the following line sets YYVAL to garbage.
       This behavior is undocumented and Bison
       users should not rely upon it.  */
    Object yyval = (0 < yylen) ? yystack.valueAt (yylen - 1) : yystack.valueAt (0);
    

    switch (yyn)
      {
          case 2:
  if (yyn == 2)
    /* "NanoParser.y":52  */
                        {generateProgram(name, ((Vector<Object[]>)(yystack.valueAt (0))));};
  break;
    

  case 3:
  if (yyn == 3)
    /* "NanoParser.y":55  */
                                        { ((Vector<Object[]>)(yystack.valueAt (1))).add(((Object[])(yystack.valueAt (0)))); yyval=((Vector<Object[]>)(yystack.valueAt (1))); };
  break;
    

  case 4:
  if (yyn == 4)
    /* "NanoParser.y":56  */
                                                { yyval=new Vector<Object[]>(); ((Vector<Object>)(yyval)).add(((Object[])(yystack.valueAt (0)))); };
  break;
    

  case 5:
  if (yyn == 5)
    /* "NanoParser.y":60  */
                       {yyval = varCount;};
  break;
    

  case 6:
  if (yyn == 6)
    /* "NanoParser.y":64  */
                        {
				varCount = 0;
				varTable = new HashMap<String,Integer>();
			};
  break;
    

  case 7:
  if (yyn == 7)
    /* "NanoParser.y":69  */
                        {
				yyval=new Object[]{((String)(yystack.valueAt (9))),((int)(yystack.valueAt (5))), ((int)(yystack.valueAt (2)))-((int)(yystack.valueAt (5))),((Vector<Object[]>)(yystack.valueAt (1))).toArray()};
			};
  break;
    

  case 10:
  if (yyn == 10)
    /* "NanoParser.y":80  */
                                      {addVar(((String)(yystack.valueAt (0))));};
  break;
    

  case 11:
  if (yyn == 11)
    /* "NanoParser.y":81  */
                     {addVar(((String)(yystack.valueAt (0))));};
  break;
    

  case 14:
  if (yyn == 14)
    /* "NanoParser.y":90  */
                               {((Vector<Object[]>)(yystack.valueAt (2))).add(((Object[])(yystack.valueAt (1)))); yyval = ((Vector<Object[]>)(yystack.valueAt (2)));};
  break;
    

  case 15:
  if (yyn == 15)
    /* "NanoParser.y":91  */
                                {yyval = new Vector<Object[]>(); ((Vector<Object>)(yyval)).add(((Object[])(yystack.valueAt (1))));};
  break;
    

  case 16:
  if (yyn == 16)
    /* "NanoParser.y":95  */
                                        {yyval = new Object[] { "RETURN", ((Object[])(yystack.valueAt (0))) };};
  break;
    

  case 17:
  if (yyn == 17)
    /* "NanoParser.y":96  */
                                                {yyval = new Object[] {"CALL", ((String)(yystack.valueAt (1))), new Object[] {((Object[])(yystack.valueAt (0)))}};};
  break;
    

  case 18:
  if (yyn == 18)
    /* "NanoParser.y":97  */
                                {yyval = new Object[] {"STORE", findVar(((String)(yystack.valueAt (2)))), ((Object[])(yystack.valueAt (0)))};};
  break;
    

  case 19:
  if (yyn == 19)
    /* "NanoParser.y":98  */
                                {yyval = new Object[] {"CALL", ((String)(yystack.valueAt (1))), new Object[]{((Object[])(yystack.valueAt (2))), ((Object[])(yystack.valueAt (0)))}};};
  break;
    

  case 20:
  if (yyn == 20)
    /* "NanoParser.y":99  */
                                {yyval = new Object[] {"CALL", ((String)(yystack.valueAt (1))), new Object[]{((Object[])(yystack.valueAt (2))), ((Object[])(yystack.valueAt (0)))}};};
  break;
    

  case 21:
  if (yyn == 21)
    /* "NanoParser.y":100  */
                                {yyval = new Object[] {"CALL", ((String)(yystack.valueAt (1))), new Object[]{((Object[])(yystack.valueAt (2))), ((Object[])(yystack.valueAt (0)))}};};
  break;
    

  case 22:
  if (yyn == 22)
    /* "NanoParser.y":101  */
                                {yyval = new Object[] {"CALL", ((String)(yystack.valueAt (1))), new Object[]{((Object[])(yystack.valueAt (2))), ((Object[])(yystack.valueAt (0)))}};};
  break;
    

  case 23:
  if (yyn == 23)
    /* "NanoParser.y":102  */
                                {yyval = new Object[] {"CALL", ((String)(yystack.valueAt (1))), new Object[]{((Object[])(yystack.valueAt (2))), ((Object[])(yystack.valueAt (0)))}};};
  break;
    

  case 24:
  if (yyn == 24)
    /* "NanoParser.y":103  */
                                {yyval = new Object[] {"CALL", ((String)(yystack.valueAt (1))), new Object[]{((Object[])(yystack.valueAt (2))), ((Object[])(yystack.valueAt (0)))}};};
  break;
    

  case 25:
  if (yyn == 25)
    /* "NanoParser.y":104  */
                                {yyval = new Object[] {"CALL", ((String)(yystack.valueAt (1))), new Object[]{((Object[])(yystack.valueAt (2))), ((Object[])(yystack.valueAt (0)))}};};
  break;
    

  case 26:
  if (yyn == 26)
    /* "NanoParser.y":105  */
                                {yyval = ((Object[])(yystack.valueAt (1)));};
  break;
    

  case 27:
  if (yyn == 27)
    /* "NanoParser.y":106  */
                                  {yyval = new Object[] {"CALL", ((String)(yystack.valueAt (3))), ((Vector<Object[]>)(yystack.valueAt (1))).toArray()};};
  break;
    

  case 28:
  if (yyn == 28)
    /* "NanoParser.y":107  */
                                     {yyval = new Object[]{"IF1", ((Object[])(yystack.valueAt (2))), ((Object[])(yystack.valueAt (0)))};};
  break;
    

  case 29:
  if (yyn == 29)
    /* "NanoParser.y":108  */
                                                    {yyval = new Object[]{"IF2", ((Object[])(yystack.valueAt (3))), ((Object[])(yystack.valueAt (1))), ((Object[])(yystack.valueAt (0)))};};
  break;
    

  case 30:
  if (yyn == 30)
    /* "NanoParser.y":109  */
                                        {yyval = new Object[]{"WHILE", ((Object[])(yystack.valueAt (2))), ((Object[])(yystack.valueAt (0)))};};
  break;
    

  case 31:
  if (yyn == 31)
    /* "NanoParser.y":110  */
                        {yyval = new Object[]{"LITERAL", ((String)(yystack.valueAt (0)))};};
  break;
    

  case 32:
  if (yyn == 32)
    /* "NanoParser.y":111  */
                     {yyval = new Object[]{"FETCH", findVar(((String)(yystack.valueAt (0))))};};
  break;
    

  case 33:
  if (yyn == 33)
    /* "NanoParser.y":115  */
                              {yyval = new Object[] { "BODY", ((Vector<Object[]>)(yystack.valueAt (1))).toArray() };};
  break;
    

  case 34:
  if (yyn == 34)
    /* "NanoParser.y":119  */
                                {yyval = new Vector<Object[]>();};
  break;
    

  case 35:
  if (yyn == 35)
    /* "NanoParser.y":120  */
                             {yyval = ((Vector<Object[]>)(yystack.valueAt (0)));};
  break;
    

  case 36:
  if (yyn == 36)
    /* "NanoParser.y":124  */
                                      {((Vector<Object[]>)(yystack.valueAt (2))).add(((Object[])(yystack.valueAt (0)))); yyval = ((Vector<Object[]>)(yystack.valueAt (2)));};
  break;
    

  case 37:
  if (yyn == 37)
    /* "NanoParser.y":125  */
                        {yyval = new Vector<Object[]>(); ((Vector<Object>)(yyval)).add(((Object[])(yystack.valueAt (0))));};
  break;
    

  case 38:
  if (yyn == 38)
    /* "NanoParser.y":129  */
                                {yyval = ((Object[])(yystack.valueAt (0)));};
  break;
    

  case 39:
  if (yyn == 39)
    /* "NanoParser.y":130  */
                                                       {yyval = new Object[]{"IF2", ((Object[])(yystack.valueAt (3))), ((Object[])(yystack.valueAt (1))), ((Object[])(yystack.valueAt (0)))};};
  break;
    

  case 40:
  if (yyn == 40)
    /* "NanoParser.y":131  */
                                        {yyval = new Object[]{"IF1", ((Object[])(yystack.valueAt (2))), ((Object[])(yystack.valueAt (0)))};};
  break;
    


/* "NanoParser.java":571  */

        default: break;
      }

    yystack.pop (yylen);
    yylen = 0;

    /* Shift the result of the reduction.  */
    int yystate = yyLRGotoState (yystack.stateAt (0), yyr1_[yyn]);
    yystack.push (yystate, yyval);
    return YYNEWSTATE;
  }


  /* Return YYSTR after stripping away unnecessary quotes and
     backslashes, so that it's suitable for yyerror.  The heuristic is
     that double-quoting is unnecessary unless the string contains an
     apostrophe, a comma, or backslash (other than backslash-backslash).
     YYSTR is taken from yytname.  */
  private final String yytnamerr_ (String yystr)
  {
    if (yystr.charAt (0) == '"')
      {
        StringBuffer yyr = new StringBuffer ();
        strip_quotes: for (int i = 1; i < yystr.length (); i++)
          switch (yystr.charAt (i))
            {
            case '\'':
            case ',':
              break strip_quotes;

            case '\\':
              if (yystr.charAt(++i) != '\\')
                break strip_quotes;
              /* Fall through.  */
            default:
              yyr.append (yystr.charAt (i));
              break;

            case '"':
              return yyr.toString ();
            }
      }
    else if (yystr.equals ("$end"))
      return "end of input";

    return yystr;
  }




  /**
   * Parse input from the scanner that was specified at object construction
   * time.  Return whether the end of the input was reached successfully.
   *
   * @return <tt>true</tt> if the parsing succeeds.  Note that this does not
   *          imply that there were no syntax errors.
   */
  public boolean parse () throws java.io.IOException

  {
    


    /* Lookahead and lookahead in internal form.  */
    int yychar = yyempty_;
    int yytoken = 0;

    /* State.  */
    int yyn = 0;
    int yylen = 0;
    int yystate = 0;
    YYStack yystack = new YYStack ();
    int label = YYNEWSTATE;

    /* Error handling.  */
    int yynerrs_ = 0;
    

    /* Semantic value of the lookahead.  */
    Object yylval = null;

    yyerrstatus_ = 0;

    /* Initialize the stack.  */
    yystack.push (yystate, yylval );



    for (;;)
      switch (label)
      {
        /* New state.  Unlike in the C/C++ skeletons, the state is already
           pushed when we come here.  */
      case YYNEWSTATE:

        /* Accept?  */
        if (yystate == yyfinal_)
          return true;

        /* Take a decision.  First try without lookahead.  */
        yyn = yypact_[yystate];
        if (yyPactValueIsDefault (yyn))
          {
            label = YYDEFAULT;
            break;
          }

        /* Read a lookahead token.  */
        if (yychar == yyempty_)
          {

            yychar = yylexer.yylex ();
            yylval = yylexer.getLVal ();

          }

        /* Convert token to internal form.  */
        yytoken = yytranslate_ (yychar);

        /* If the proper action on seeing token YYTOKEN is to reduce or to
           detect an error, take that action.  */
        yyn += yytoken;
        if (yyn < 0 || yylast_ < yyn || yycheck_[yyn] != yytoken)
          label = YYDEFAULT;

        /* <= 0 means reduce or error.  */
        else if ((yyn = yytable_[yyn]) <= 0)
          {
            if (yyTableValueIsError (yyn))
              label = YYERRLAB;
            else
              {
                yyn = -yyn;
                label = YYREDUCE;
              }
          }

        else
          {
            /* Shift the lookahead token.  */
            /* Discard the token being shifted.  */
            yychar = yyempty_;

            /* Count tokens shifted since error; after three, turn off error
               status.  */
            if (yyerrstatus_ > 0)
              --yyerrstatus_;

            yystate = yyn;
            yystack.push (yystate, yylval);
            label = YYNEWSTATE;
          }
        break;

      /*-----------------------------------------------------------.
      | yydefault -- do the default action for the current state.  |
      `-----------------------------------------------------------*/
      case YYDEFAULT:
        yyn = yydefact_[yystate];
        if (yyn == 0)
          label = YYERRLAB;
        else
          label = YYREDUCE;
        break;

      /*-----------------------------.
      | yyreduce -- Do a reduction.  |
      `-----------------------------*/
      case YYREDUCE:
        yylen = yyr2_[yyn];
        label = yyaction (yyn, yystack, yylen);
        yystate = yystack.stateAt (0);
        break;

      /*------------------------------------.
      | yyerrlab -- here on detecting error |
      `------------------------------------*/
      case YYERRLAB:
        /* If not already recovering from an error, report this error.  */
        if (yyerrstatus_ == 0)
          {
            ++yynerrs_;
            if (yychar == yyempty_)
              yytoken = yyempty_;
            yyerror (yysyntax_error (yystate, yytoken));
          }

        
        if (yyerrstatus_ == 3)
          {
            /* If just tried and failed to reuse lookahead token after an
               error, discard it.  */

            if (yychar <= Lexer.EOF)
              {
                /* Return failure if at end of input.  */
                if (yychar == Lexer.EOF)
                  return false;
              }
            else
              yychar = yyempty_;
          }

        /* Else will try to reuse lookahead token after shifting the error
           token.  */
        label = YYERRLAB1;
        break;

      /*-------------------------------------------------.
      | errorlab -- error raised explicitly by YYERROR.  |
      `-------------------------------------------------*/
      case YYERROR:
        
        /* Do not reclaim the symbols of the rule which action triggered
           this YYERROR.  */
        yystack.pop (yylen);
        yylen = 0;
        yystate = yystack.stateAt (0);
        label = YYERRLAB1;
        break;

      /*-------------------------------------------------------------.
      | yyerrlab1 -- common code for both syntax error and YYERROR.  |
      `-------------------------------------------------------------*/
      case YYERRLAB1:
        yyerrstatus_ = 3;       /* Each real token shifted decrements this.  */

        for (;;)
          {
            yyn = yypact_[yystate];
            if (!yyPactValueIsDefault (yyn))
              {
                yyn += yy_error_token_;
                if (0 <= yyn && yyn <= yylast_ && yycheck_[yyn] == yy_error_token_)
                  {
                    yyn = yytable_[yyn];
                    if (0 < yyn)
                      break;
                  }
              }

            /* Pop the current state because it cannot handle the
             * error token.  */
            if (yystack.height == 0)
              return false;

            
            yystack.pop ();
            yystate = yystack.stateAt (0);
          }

        if (label == YYABORT)
            /* Leave the switch.  */
            break;



        /* Shift the error token.  */

        yystate = yyn;
        yystack.push (yyn, yylval);
        label = YYNEWSTATE;
        break;

        /* Accept.  */
      case YYACCEPT:
        return true;

        /* Abort.  */
      case YYABORT:
        return false;
      }
}




  // Generate an error message.
  private String yysyntax_error (int yystate, int tok)
  {
    if (yyErrorVerbose)
      {
        /* There are many possibilities here to consider:
           - If this state is a consistent state with a default action,
             then the only way this function was invoked is if the
             default action is an error action.  In that case, don't
             check for expected tokens because there are none.
           - The only way there can be no lookahead present (in tok) is
             if this state is a consistent state with a default action.
             Thus, detecting the absence of a lookahead is sufficient to
             determine that there is no unexpected or expected token to
             report.  In that case, just report a simple "syntax error".
           - Don't assume there isn't a lookahead just because this
             state is a consistent state with a default action.  There
             might have been a previous inconsistent state, consistent
             state with a non-default action, or user semantic action
             that manipulated yychar.  (However, yychar is currently out
             of scope during semantic actions.)
           - Of course, the expected token list depends on states to
             have correct lookahead information, and it depends on the
             parser not to perform extra reductions after fetching a
             lookahead from the scanner and before detecting a syntax
             error.  Thus, state merging (from LALR or IELR) and default
             reductions corrupt the expected token list.  However, the
             list is correct for canonical LR with one exception: it
             will still contain any token that will not be accepted due
             to an error action in a later state.
        */
        if (tok != yyempty_)
          {
            /* FIXME: This method of building the message is not compatible
               with internationalization.  */
            StringBuffer res =
              new StringBuffer ("syntax error, unexpected ");
            res.append (yytnamerr_ (yytname_[tok]));
            int yyn = yypact_[yystate];
            if (!yyPactValueIsDefault (yyn))
              {
                /* Start YYX at -YYN if negative to avoid negative
                   indexes in YYCHECK.  In other words, skip the first
                   -YYN actions for this state because they are default
                   actions.  */
                int yyxbegin = yyn < 0 ? -yyn : 0;
                /* Stay within bounds of both yycheck and yytname.  */
                int yychecklim = yylast_ - yyn + 1;
                int yyxend = yychecklim < yyntokens_ ? yychecklim : yyntokens_;
                int count = 0;
                for (int x = yyxbegin; x < yyxend; ++x)
                  if (yycheck_[x + yyn] == x && x != yy_error_token_
                      && !yyTableValueIsError (yytable_[x + yyn]))
                    ++count;
                if (count < 5)
                  {
                    count = 0;
                    for (int x = yyxbegin; x < yyxend; ++x)
                      if (yycheck_[x + yyn] == x && x != yy_error_token_
                          && !yyTableValueIsError (yytable_[x + yyn]))
                        {
                          res.append (count++ == 0 ? ", expecting " : " or ");
                          res.append (yytnamerr_ (yytname_[x]));
                        }
                  }
              }
            return res.toString ();
          }
      }

    return "syntax error";
  }

  /**
   * Whether the given <code>yypact_</code> value indicates a defaulted state.
   * @param yyvalue   the value to check
   */
  private static boolean yyPactValueIsDefault (int yyvalue)
  {
    return yyvalue == yypact_ninf_;
  }

  /**
   * Whether the given <code>yytable_</code>
   * value indicates a syntax error.
   * @param yyvalue the value to check
   */
  private static boolean yyTableValueIsError (int yyvalue)
  {
    return yyvalue == yytable_ninf_;
  }

  private static final short yypact_ninf_ = -40;
  private static final byte yytable_ninf_ = -7;

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short yypact_[] = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
     -40,     3,    43,   -40,    45,   -40,   -40,    29,    61,   -40,
      38,    46,   -40,    69,    59,   -40,    73,    61,   -40,    18,
      93,   -40,    20,   -40,   -40,   -40,   -40,   -40,   -40,   -40,
     -40,    65,    68,    93,    93,    49,    16,    93,    93,    93,
      93,    93,   163,   111,   -40,    23,    93,    93,    93,    93,
      93,    93,    93,   -40,   -40,   163,   163,    72,    66,   118,
     137,   -40,   -40,   129,   129,     2,    27,    57,    80,   -40,
     -40,    93,    70,    70,   163,    93,    51,   -40,    71,    70,
      83,   -40,   -40,   -40,    93,   144,    70,    51,   -40
    };
  }

/* YYDEFACT[STATE-NUM] -- Default reduction number in state STATE-NUM.
   Performed when YYTABLE does not specify something else to do.  Zero
   means the default is an error.  */
  private static final byte yydefact_[] = yydefact_init();
  private static final byte[] yydefact_init()
  {
    return new byte[]
    {
       6,     0,     2,     4,     0,     1,     3,     0,     8,    11,
       0,     9,     5,     0,     0,    10,    12,     0,     5,     0,
       0,    13,    32,    41,    42,    43,    44,    45,    46,    47,
      31,     0,     0,     0,     0,     0,     0,     0,     0,    34,
       0,     0,    16,     0,     7,     0,     0,     0,     0,     0,
       0,     0,     0,    15,    17,    18,    37,     0,    35,     0,
       0,    26,    14,    19,    20,    21,    22,    23,    24,    25,
      27,     0,     0,     0,    36,     0,    28,    30,     0,     0,
       0,    29,    33,    38,     0,     0,     0,    40,    39
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final byte yypgoto_[] = yypgoto_init();
  private static final byte[] yypgoto_init()
  {
    return new byte[]
    {
     -40,   -40,   -40,    90,   108,   -40,   -40,    94,   -40,    37,
     -33,   -39,   -40,   -40,    26,   -40
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final byte yydefgoto_[] = yydefgoto_init();
  private static final byte[] yydefgoto_init()
  {
    return new byte[]
    {
      -1,     1,     2,    14,     3,     4,    10,    11,    18,    35,
      36,    76,    57,    58,    81,    37
    };
  }

/* YYTABLE[YYPACT[STATE-NUM]] -- What to do in state STATE-NUM.  If
   positive, shift that token.  If negative, reduce the rule whose
   number is the opposite.  If YYTABLE_NINF, syntax error.  */
  private static final byte yytable_[] = yytable_init();
  private static final byte[] yytable_init()
  {
    return new byte[]
    {
      42,    43,    45,     5,    54,    55,    56,    59,    60,    49,
      50,    51,    52,    63,    64,    65,    66,    67,    68,    69,
      46,    47,    48,    49,    50,    51,    52,    46,    47,    48,
      49,    50,    51,    52,    77,    50,    51,    52,    74,    38,
      83,    39,    53,    13,    21,    45,    -6,    87,     7,    62,
       8,    85,    22,    23,    24,    25,    26,    27,    28,    29,
      12,    30,    31,    32,     9,    33,    51,    52,    79,    80,
      34,    13,    15,    44,    22,    23,    24,    25,    26,    27,
      28,    29,    16,    30,    31,    32,    40,    33,    17,    41,
      52,    71,    34,    75,    70,    82,    22,    23,    24,    25,
      26,    27,    28,    29,    84,    30,    31,    32,    20,    33,
       6,    19,    78,    88,    34,    46,    47,    48,    49,    50,
      51,    52,    46,    47,    48,    49,    50,    51,    52,     0,
       0,     0,     0,    61,    47,    48,    49,    50,    51,    52,
      72,    46,    47,    48,    49,    50,    51,    52,    46,    47,
      48,    49,    50,    51,    52,     0,     0,     0,     0,    73,
       0,     0,     0,     0,     0,     0,    86,    46,    47,    48,
      49,    50,    51,    52
    };
  }

private static final byte yycheck_[] = yycheck_init();
  private static final byte[] yycheck_init()
  {
    return new byte[]
    {
      33,    34,    35,     0,    37,    38,    39,    40,    41,     7,
       8,     9,    10,    46,    47,    48,    49,    50,    51,    52,
       4,     5,     6,     7,     8,     9,    10,     4,     5,     6,
       7,     8,     9,    10,    73,     8,     9,    10,    71,    19,
      79,    21,    26,    25,    26,    78,     3,    86,     3,    26,
      21,    84,     3,     4,     5,     6,     7,     8,     9,    10,
      22,    12,    13,    14,     3,    16,     9,    10,    17,    18,
      21,    25,     3,    24,     3,     4,     5,     6,     7,     8,
       9,    10,    23,    12,    13,    14,    21,    16,    15,    21,
      10,    25,    21,    23,    22,    24,     3,     4,     5,     6,
       7,     8,     9,    10,    21,    12,    13,    14,    18,    16,
       2,    17,    75,    87,    21,     4,     5,     6,     7,     8,
       9,    10,     4,     5,     6,     7,     8,     9,    10,    -1,
      -1,    -1,    -1,    22,     5,     6,     7,     8,     9,    10,
      22,     4,     5,     6,     7,     8,     9,    10,     4,     5,
       6,     7,     8,     9,    10,    -1,    -1,    -1,    -1,    22,
      -1,    -1,    -1,    -1,    -1,    -1,    22,     4,     5,     6,
       7,     8,     9,    10
    };
  }

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
  private static final byte yystos_[] = yystos_init();
  private static final byte[] yystos_init()
  {
    return new byte[]
    {
       0,    28,    29,    31,    32,     0,    31,     3,    21,     3,
      33,    34,    22,    25,    30,     3,    23,    15,    35,    34,
      30,    26,     3,     4,     5,     6,     7,     8,     9,    10,
      12,    13,    14,    16,    21,    36,    37,    42,    19,    21,
      21,    21,    37,    37,    24,    37,     4,     5,     6,     7,
       8,     9,    10,    26,    37,    37,    37,    39,    40,    37,
      37,    22,    26,    37,    37,    37,    37,    37,    37,    37,
      22,    25,    22,    22,    37,    23,    38,    38,    36,    17,
      18,    41,    24,    38,    21,    37,    22,    38,    41
    };
  }

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
  private static final byte yyr1_[] = yyr1_init();
  private static final byte[] yyr1_init()
  {
    return new byte[]
    {
       0,    27,    28,    29,    29,    30,    32,    31,    33,    33,
      34,    34,    35,    35,    36,    36,    37,    37,    37,    37,
      37,    37,    37,    37,    37,    37,    37,    37,    37,    37,
      37,    37,    37,    38,    39,    39,    40,    40,    41,    41,
      41,    42,    42,    42,    42,    42,    42,    42
    };
  }

/* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
  private static final byte yyr2_[] = yyr2_init();
  private static final byte[] yyr2_init()
  {
    return new byte[]
    {
       0,     2,     1,     2,     1,     0,     0,    11,     0,     1,
       3,     1,     0,     3,     3,     2,     2,     2,     3,     3,
       3,     3,     3,     3,     3,     3,     3,     4,     5,     6,
       5,     1,     1,     3,     0,     1,     3,     1,     2,     6,
       5,     1,     1,     1,     1,     1,     1,     1
    };
  }


  /* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
     First, the terminals, then, starting at \a yyntokens_, nonterminals.  */
  private static final String yytname_[] = yytname_init();
  private static final String[] yytname_init()
  {
    return new String[]
    {
  "$end", "error", "$undefined", "NAME", "OP1", "OP2", "OP3", "OP4",
  "OP5", "OP6", "OP7", "ERROR", "LITERAL", "IF", "WHILE", "VAR", "RETURN",
  "ELSE", "ELSIF", "'='", "UNOP", "'('", "')'", "'{'", "'}'", "','", "';'",
  "$accept", "start", "program", "varCount", "fundecl", "$@1", "pars",
  "nonemptypars", "decls", "exprs", "expr", "body", "args", "nonemptyargs",
  "nonemptyifrest", "op", null
    };
  }



  /* YYTRANSLATE_(TOKEN-NUM) -- Symbol number corresponding to TOKEN-NUM
     as returned by yylex, with out-of-bounds checking.  */
  private static final byte yytranslate_ (int t)
  {
    int user_token_number_max_ = 274;
    byte undef_token_ = 2;

    if (t <= 0)
      return Lexer.EOF;
    else if (t <= user_token_number_max_)
      return yytranslate_table_[t];
    else
      return undef_token_;
  }
  private static final byte yytranslate_table_[] = yytranslate_table_init();
  private static final byte[] yytranslate_table_init()
  {
    return new byte[]
    {
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
      21,    22,     2,     2,    25,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,    26,
       2,    19,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,    23,     2,    24,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    20
    };
  }


  private static final byte yy_error_token_ = 1;

  private static final int yylast_ = 173;
  private static final int yynnts_ = 16;
  private static final int yyempty_ = -2;
  private static final int yyfinal_ = 5;
  private static final int yyntokens_ = 27;

/* User implementation code.  */
/* Unqualified %code blocks.  */
/* "NanoParser.y":12  */

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

/* "NanoParser.java":1208  */

}

