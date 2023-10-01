// PL0AST.java

// This program is a recursive descent parser for PL/0. The abstract syntax 
// tree representation for the program is constructed.

public class PL0AST {

  public static void main (String args []) throws java.io.IOException {
    System . out . println ("Source Program");
    System . out . println ("--------------");
    System . out . println ();
    String str;
    ParserAST pl0 = new ParserAST ();
    Program program; 
    program = null;
    program = pl0 . CompilationUnit ();
    System . out . println ();
    System . out . println ();
    //System . out . println ("Abstract Syntax Tree" );
    //System . out . println ("--------------------");
    //System . out . println ();
    //System . out . println (program);
  }

}
