// ParserAST implementation
// ParserAST is a class to represent a recursive descent parser for the 
// PL/0 programming language, described in Algorithms + Data Structures = 
// Programs by Niklaus Wirth, Prentice-Hall, 1976.

import java.io.*;

public class ParserAST {

  protected PL0Lexer lexer;	// lexical analyzer
  protected PL0Lexer lexer1;
  protected Token token;	// current token

  public ParserAST () throws IOException {
    lexer = new PL0Lexer (new InputStreamReader (System . in));
    //lexer1 = lexer;
    getToken ();           	// get first token
  }

  private void getToken () throws IOException { 
    token = lexer . nextToken ();
     
  }

  // <program> ::= <block> . 
 public static String name ;
  public void program () throws IOException {
    Program program = null;
    //while(token . symbol() != Symbol . EOF){
    CompilationUnit();
    //}
    // <block>
    if (token . symbol () != Symbol . EOF) 	// .
      ErrorMessage . print (lexer . position (), "EOF EXPECTED");
   // return program;
  }
public Program CompilationUnit() throws IOException{
    //System.out.println(token.symbol()+" "+Symbol.OBJECT);
    Program stmt = null,stmt1;
    String str;
    int c =0 ;
    if(token.symbol() == Symbol . OBJECT){
        getToken();
        if(token.symbol() == Symbol.ID){
	    //str = token . lexeme ();
            getToken();
        }else{
		str= null;
            ErrorMessage. print(lexer.position(), "ID Expected");
        }
        if(token.symbol() == Symbol.LBRACES) getToken();
        else ErrorMessage. print(lexer.position(), "{ Expected");
        while(token.symbol() == Symbol.DEF || token . symbol () == Symbol . VAR){
		//if(stmt == null)
		if(token . symbol() == Symbol . DEF ){

		stmt = Def();
		System . out . println ();
		System . out . println ();
		System . out . println ("Abstract Syntax Tree " + name );
                System . out . println ("------------------------------");
		//else{
		//	stmt1 = Def();
		//	stmt = new Statementc(stmt,stmt1);
		//}
		//return stmt;
		//stmt = new  Program (stmt);
    		System . out . println ();
    		System . out . println (stmt);
		}else stmt = Def();
	}
        if(token.symbol()==Symbol.RBRACES) getToken();
        else ErrorMessage. print(lexer.position(), "} Expected");
    } else{
        ErrorMessage. print(lexer.position(), "object Expected");
    }
    //stmt = null;
    return stmt;
}

public Statementc MainDef() throws IOException{
    Statementc stmt = null,stmt1 = null, stmt2 =null;
    getToken();
    name = "main";
    if(token.symbol() == Symbol.LPAREN) getToken();
    else ErrorMessage. print(lexer.position(), "( Expected");
    if(token.symbol() == Symbol.ARGS) getToken();
    else ErrorMessage. print(lexer.position(), "args Expected");
    if(token.symbol() == Symbol.COLON) getToken();
    else ErrorMessage. print(lexer.position(), ": Expected");
    if(token.symbol() == Symbol.ARRAY) getToken();
    else ErrorMessage. print(lexer.position(), "Array Expected");
    if(token.symbol() == Symbol.LBRACKET) getToken();
    else ErrorMessage. print(lexer.position(), "[ Expected");
    if(token.symbol() == Symbol.STRING) getToken();
    else ErrorMessage. print(lexer.position(), "value Expected");
    if(token.symbol() == Symbol.RBRACKET) getToken();
    else ErrorMessage. print(lexer.position(), "] Expected");
    if(token.symbol() == Symbol.RPAREN) getToken();
    else ErrorMessage. print(lexer.position(), ") Expected");
    if(token.symbol() == Symbol.LBRACES) getToken();
    else ErrorMessage. print(lexer.position(), "{ Expected");
    while(token.symbol() == Symbol.VAR) VarDef();
    stmt = Statement();
    //stmt = new Statementc(stmt1,stmt);
    while(token.symbol() == Symbol.IF || token.symbol() == Symbol.WHILE || token.symbol() == Symbol.ID || token.symbol()==Symbol.PRINT || token.symbol() == Symbol.LPAREN){
    	    stmt2 = Statement();
	    stmt = new Statementc(stmt,stmt2);
}
    if(token.symbol() == Symbol.RBRACES) getToken();
    else ErrorMessage. print(lexer.position(), "} Expected");
    return stmt;
}

public Program Def() throws IOException{
    Program program = null;
    Statementc stmt = null,stmt1;
    Identifier id;
    String str = null;
    Expression expr;
    lexer1 = lexer;
    if(token.symbol()== Symbol.DEF){
	    //str = lexer1 . nextToken() . lexeme();  
        getToken();
	if(token . symbol() == Symbol . MAIN){
		stmt = MainDef();
		program = stmt;
		System . out . println ();
       // System . out . println ();
       // System . out . println ("Abstract Syntax Tree main" );
       // System . out . println ("------------------------------");
        //System . out . println ();
       // System . out . println (stmt);
		return stmt;
	}
        if(token.symbol() == Symbol.ID){
		name = token . lexeme();
		getToken();
	}
        else{
		str = null;
	       	ErrorMessage. print(lexer.position(), "ID Expected");
	}
        //System . out . println ();
	//System . out . println ();
	//System . out . println ("Abstract Syntax Tree" + str );
        //System . out . println ("------------------------------");
	//System . out . println ();
        //System . out . println (stmt);
        if(token.symbol() == Symbol.LPAREN) getToken();
        else ErrorMessage. print(lexer.position(), "( Expected");
        if(token.symbol() == Symbol.ID) getToken();
        else ErrorMessage. print(lexer.position(), "ID Expected");
        if(token.symbol() == Symbol.COLON) getToken();
        else ErrorMessage. print(lexer.position(), ": Expected");
        Type();
        while(token.symbol() == Symbol.COMMA){
            getToken();
            if(token.symbol() == Symbol.ID) getToken();
            else ErrorMessage. print(lexer.position(), "ID Expected");
            if(token.symbol() == Symbol.COLON) getToken();
            else ErrorMessage. print(lexer.position(), ": Expected");
            Type();
        }
        if(token.symbol() == Symbol.RPAREN) getToken();
        else ErrorMessage. print(lexer.position(), ") Expected");
        if(token.symbol() == Symbol.COLON) getToken();
        else ErrorMessage. print(lexer.position(), ": Expected");
        Type();
        if(token.symbol() == Symbol.EQ) getToken();
        else ErrorMessage. print(lexer.position(), "= Expected");
        if(token.symbol() == Symbol.LBRACES) getToken();
        else ErrorMessage. print(lexer.position(), "{ Expected");
        while(token.symbol()== Symbol.VAR) VarDef();
	//System . out . println ("Helllllll");
        while(token.symbol() == Symbol.IF || token.symbol() == Symbol.WHILE || token.symbol() == Symbol.ID || token.symbol()==Symbol.PRINT || token.symbol() == Symbol.LPAREN){
            	//System . out . println ("Helllllll");
		if(stmt == null)
		stmt = Statement();
		else{
			stmt1 = Statement();
			stmt = new Statementc(stmt,stmt1);
		}
        }
        if(token.symbol() == Symbol.RETURN) getToken();
        else ErrorMessage. print(lexer.position(), "return Expected");
        expr = ListExpr();
	stmt1 = new ReturnStatement(expr);
	stmt = new Statementc(stmt,stmt1);
        if(token.symbol() == Symbol.SEMICOLON) getToken();
        else ErrorMessage. print(lexer.position(), "; Expected");
        if(token.symbol()==Symbol.RBRACES) getToken();
        else ErrorMessage. print(lexer.position(), "} Expected");
    }
    else if(token.symbol() == Symbol.VAR){
        VarDef();
    }
    else ErrorMessage. print(lexer.position(), "var or def Expected");
    program = stmt;
        //System . out . println ();
       // System . out . println ();
       // System . out . println ("Abstract Syntax Tree" + str );
       // System . out . println ("------------------------------");
       // System . out . println ();
       // System . out . println (stmt);
    return stmt;
}

public void VarDef() throws IOException{
    Statementc stmt= null;
    Identifier id = null;
    Expression expr;
    if(token.symbol() == Symbol.VAR) getToken();
    else ErrorMessage. print(lexer.position(), "var Expected");
    if(token.symbol() == Symbol.ID){
	//    id = new Identifier(token . lexeme());
	    getToken();
    }
    else ErrorMessage. print(lexer.position(), "ID Expected");
    if(token.symbol() == Symbol.COLON) getToken();
    else ErrorMessage. print(lexer.position(), ": Expected");
    Type();
    if(token.symbol() == Symbol.EQ) getToken();
    else ErrorMessage. print(lexer.position(), "= Expected");
    Literal();
    if(token.symbol() == Symbol.SEMICOLON) getToken();
    else ErrorMessage. print(lexer.position(), "; Expected");
   // return stmt;
}

public void Type() throws IOException{
    if(token.symbol() == Symbol.INT) getToken();
    else if(token.symbol() == Symbol.LIST){
        getToken();
        if(token.symbol() == Symbol.LBRACKET) getToken();
        else ErrorMessage. print(lexer.position(), "[ Expected");
        if(token.symbol() == Symbol.INT) getToken();
        else ErrorMessage. print(lexer.position(), "int Expected");
        if(token.symbol() == Symbol.RBRACKET) getToken();
        else ErrorMessage. print(lexer.position(), "] Expected");
    }
    else ErrorMessage. print(lexer.position(), "int or list Expected");
}

public Statementc Statement() throws IOException{
    Statementc stmt=null,stmt1 =null;
    Expression expr;
    Identifier id = null;
    if(token.symbol() == Symbol.IF){
         getToken();
         if(token.symbol() == Symbol.LPAREN) getToken();
         else ErrorMessage. print(lexer.position(), "( Expected");
         expr = Expr();
         if(token.symbol() == Symbol.RPAREN) getToken();
         else ErrorMessage. print(lexer.position(), ") Expected");
	 stmt = Statement();
	 if(token.symbol() == Symbol.ELSE){
		 getToken();
		 stmt1 = Statement();
	 }
	 stmt = new IfStatement(expr , stmt, stmt1);
    }else if(token.symbol() == Symbol.WHILE){
         getToken();
         if(token.symbol() == Symbol.LPAREN) getToken();
         else ErrorMessage. print(lexer.position(), "( Expected");
         expr = Expr();
         if(token.symbol() == Symbol.RPAREN) getToken();
         else ErrorMessage. print(lexer.position(), ") Expected");
	// System.out.println ("hello" +token . symbol());
         stmt = Statement();
	 stmt = new WhileStatement(expr , stmt);
    }else if(token.symbol() == Symbol.ID){
	id = new Identifier(token . lexeme());
        getToken();
        if(token.symbol() == Symbol.EQ) getToken();
        else ErrorMessage. print(lexer.position(), "= Expected");
	expr = ListExpr();
	stmt = new Assignment(id , expr);
        if(token.symbol() == Symbol.SEMICOLON) getToken();
        else ErrorMessage. print(lexer.position(), "; Expected");
    }else if(token.symbol() == Symbol.PRINT){
        getToken();
        if(token.symbol() == Symbol.LPAREN) getToken();
         else ErrorMessage. print(lexer.position(), "( Expected");
	 //System.out.println(Integer . parseInt (token . lexeme ()));
         expr = ListExpr();
         if(token.symbol() == Symbol.RPAREN) getToken();
         else ErrorMessage. print(lexer.position(), ") Expected");
         if(token.symbol() == Symbol.SEMICOLON) getToken();
        else ErrorMessage. print(lexer.position(), "; Expected");
	stmt = new PrintStatement(expr);
    }else if(token.symbol() == Symbol.LBRACES){
        getToken();
        stmt = Statement();
        while(token.symbol() == Symbol.IF || token.symbol() == Symbol.WHILE || token.symbol() == Symbol.ID || token.symbol()==Symbol.PRINT || token.symbol() == Symbol.LPAREN){
            	//if(stmt == null){
		//stmt = Statement();
		//}else{
			stmt1 = Statement();
		//	if(stmt1!= null)
			stmt = new Statementc(stmt,stmt1); 
		//}
        }
        if(token.symbol() == Symbol.RBRACES) getToken();
         else ErrorMessage. print(lexer.position(), "} Expected");
    }
    return stmt;
    //else ErrorMessage. print(lexer.position(), "if or while or ID or print or { Expected");
}

public Expression Expr() throws IOException{
    Expression expr,expr1;
    String str;
    expr = AndExpr();
    while(token.symbol() == Symbol.DOR){
	str = "||";
        expr1 = AndExpr();
	expr = new Binary(str, expr1,expr);
    }
    return expr;
}

public Expression AndExpr() throws IOException{
    Expression expr1 , expr2;
    String str;
    expr1 = RelExpr();
    while(token.symbol() == Symbol.DAND) {
        str = "&&";
	    getToken();
	    expr2 = RelExpr();
        expr1 = new Binary(str, expr1,expr2);
    }

    return expr1;
}

public Expression RelExpr() throws IOException{
    Expression expr1,expr2;
    String str1,str2;
    if(token.symbol() == Symbol.NOT){
        str1 = "!";
        getToken();
   }else str1 = null;
    expr1 = ListExpr();
    if(token.symbol() == Symbol.LT || token.symbol() == Symbol.GT || token.symbol() == Symbol.LE || token.symbol() == Symbol.GE || token.symbol() == Symbol.DEQ || token.symbol() == Symbol.NOTEQ){
        str2 = RelOper();
        expr2 = ListExpr();
        expr1 = new Binary(str2,expr1,expr2);
    }
    if(str1 == "!" ) expr1 = new Unary(str1,expr1);
    return expr1;
}

public String RelOper() throws IOException{
    String str;
    //System.out.println(token.lexeme()+"hello");
    if(token.symbol() == Symbol.LT){
        str = "<";
        getToken();
   }
    else if(token.symbol() == Symbol.GT){
        str = ">";
        getToken();
   }
    else if(token.symbol() == Symbol.LE){
	//System.out.println("Hello");
        str = "<=";
        getToken();
   }
    else if(token.symbol() == Symbol.GE){
        str = ">=";
        getToken();
   }
    else if(token.symbol() == Symbol.DEQ){
        str = "==";
        getToken();
   }
    else if(token.symbol() == Symbol.NOTEQ){
        str = "!=";
        getToken();
   }
    else{
	    str = "";
	    ErrorMessage. print(lexer.position(), "relational operator Expected");
    }
    System.out.println(token.lexeme());
    return str;
}

public Expression ListExpr() throws IOException{
    Expression expr1,expr2;
    String str;
   // System.out.println(token . lexeme());
   //if(token . symbol() == Symbol .ID	) System.out.println("HEllllllo2");
    expr1 = AddExpr();
    if(token.symbol()==Symbol.DCOLON){
        str = "::";
        getToken();
        expr2 = ListExpr();
	expr1 = new Binary(str,expr1,expr2);
    }
    return expr1;
}

public Expression AddExpr() throws IOException{
    Expression expr1,expr2;
    String str;
    if(token . symbol() == Symbol . MINUS){
	str = "-";
    	expr1 = MulExpr();
	expr1 = new Unary(str, expr1);
	return expr1;
    }
   // if(token . symbol() == Symbol .ID) System.out.println("HEllllllo3");
    expr1 = MulExpr();
    while(token.symbol() == Symbol.PLUS || token.symbol() == Symbol.MINUS){
        str = AddOper();
        expr2 = MulExpr();
        expr1 = new Binary(str, expr1, expr2);
    }
    return expr1;
}

public String AddOper() throws IOException{
    String str;
    if(token.symbol()==Symbol.PLUS){
        str = "+";
        getToken();
   }else if(token.symbol() == Symbol.MINUS){
        str = "-";
        getToken();
   }
    else{
	    str = "";
	    ErrorMessage. print(lexer.position(), "add operator Expected");
    }
    return str;
}

public Expression MulExpr() throws IOException{
    Expression expr1,expr2;
    String str;
    //if(token . symbol() == Symbol .ID) System.out.println("HEllllllo4");
    expr1  = PrefixExpr();
    while(token.symbol() == Symbol.TIMES || token.symbol() == Symbol.SLASH){
        str = MulOper();
        expr2 = PrefixExpr();
        expr1 = new Binary(str, expr1, expr2);
    }
    return expr1;
}

public String MulOper() throws IOException{
    String str;
    if(token.symbol()==Symbol.TIMES){
         str = "*";
         getToken();
    }
    else if(token.symbol() == Symbol.SLASH){
        str = "/";
        getToken();
   }
    else{
	    str = "";
	    ErrorMessage. print(lexer.position(), "mul operator Expected");
    }
    return str;
}

public Expression PrefixExpr() throws IOException{
    Expression expr;
    String str;
    if(token.symbol() == Symbol.PLUS || token.symbol() == Symbol.MINUS) getToken();
  //  if(token . symbol() == Symbol .ID) System.out.println("HEllllllo5");
    expr = SimpleExpr();
    while(token.symbol()==Symbol.PERIOD){
        str = ListMethodCall();
    	expr = new Unary(str , expr);
    }
    return expr;
}

public String ListMethodCall() throws IOException{
	String str;
    if(token.symbol()==Symbol.PERIOD){
	getToken();
        if(token.symbol() == Symbol.HEAD){
		str = "head";
		getToken();
	}
        else if(token.symbol() == Symbol.TAIL){
		str = "tail";
		getToken();
	}
        else if(token.symbol() == Symbol.ISEMPTY){
	       	str = "isEmpty";
		getToken();
	}
        else{
		str = null;
	       	ErrorMessage. print(lexer.position(), "Call Expected");
	}
    }else{
	    str = null;
	    ErrorMessage. print(lexer.position(), ". Expected");
    }
    return str;
}

public Expression SimpleExpr() throws IOException{
    Expression expr = null ,expr1;
    Identifier id ;
    String str;
    if(token.symbol() == Symbol.INTEGER || token.symbol() == Symbol.NIL) expr = Literal();
    else if(token.symbol() == Symbol.LPAREN){
        getToken();
        expr = Expr();
        if(token.symbol() == Symbol.RPAREN) getToken();
        else ErrorMessage. print(lexer.position(), ") Expected");
    }else if(token.symbol() == Symbol.ID){
        str = token . lexeme ();
	//if(token . symbol() == Symbol .ID) System.out.println("HEllllllo6");
        getToken();
	//if(token . symbol() == Symbol .ID) System.out.println("HEllllllo6");
        if(token.symbol() == Symbol.LPAREN){
		getToken();
	//System.out.println(token . symbol());
	//if(token . symbol() == Symbol .ID) System.out.println("HEllllllo1");
        expr = ListExpr();
	//expr  = new Call(null,expr, expr1);
        while(token.symbol() == Symbol.COMMA){
            getToken();
	    //if(token . symbol() == Symbol .ID) System.out.println("HEllllllo");
	    expr1 = ListExpr();
	    expr = new Expression(expr,expr1);
        }
	//expr = expr1;
	expr = new Call(str,expr);
        if(token.symbol() == Symbol.RPAREN) getToken();
        else ErrorMessage. print(lexer.position(), ") Expected");
	}else{
		//System.out.println(str);
		expr = new Identifier(str);
	}
    }else if(token.symbol() == Symbol.SCALA){
        getToken();
        if(token.symbol() == Symbol.PERIOD) getToken();
        else ErrorMessage. print(lexer.position(), ". Expected");
        if(token.symbol() == Symbol.IO) getToken();
        else ErrorMessage. print(lexer.position(), "IO Expected");
        if(token.symbol() == Symbol.PERIOD) getToken();
        else ErrorMessage. print(lexer.position(), ". Expected");
        if(token.symbol() == Symbol.STDIN) getToken();
        else ErrorMessage. print(lexer.position(), "StdIn Expected");
        if(token.symbol() == Symbol.PERIOD) getToken();
        else ErrorMessage. print(lexer.position(), ". Expected");
        if(token.symbol() == Symbol.READINT){
		expr = new Call("readInt");
		getToken();
	}
        else ErrorMessage. print(lexer.position(), "ReadInt Expected");
        if(token.symbol() == Symbol.LPAREN) getToken();
        else ErrorMessage. print(lexer.position(), "( Expected");
        if(token.symbol() == Symbol.RPAREN) getToken();
        else ErrorMessage. print(lexer.position(), ") Expected");
    }
    return expr;
}

public Expression Literal() throws IOException{
    Expression expr = null;
    if(token.symbol() == Symbol.INTEGER){
        expr = new IntValue (Integer . parseInt (token . lexeme ()));
//	System.out.println(token . lexeme());
	getToken();

    }
    else if(token.symbol() == Symbol.NIL){
        getToken();
    }
    else ErrorMessage. print(lexer.position(), "integer Expected");
    return expr;
}
}
