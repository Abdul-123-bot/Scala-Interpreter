class Program {

	protected Program prog;
	public Program() { }

	public Program(Program prog){
		this . prog = prog;
	}
	public String toString () {
	  return ""+prog;
	}

}

class Statementc extends Program {

   protected Statementc stmt1, stmt2;

   public Statementc () { }

   public Statementc (Statementc stmt1, Statementc stmt2) {
     this . stmt1 = stmt1;
     this . stmt2 = stmt2;
   }

   public String toString () {
     if (stmt1 == null)
       return "()";
     else if (stmt2 == null)
       return "(" + stmt2 + ")";
     else
       return "(: " + stmt1 + " " + stmt2 + ")";
   }

}
class ReturnStatement extends Statementc{
   protected Expression expr;
  // protected Statement;

   public ReturnStatement(){ }

   public ReturnStatement (Expression expr){
   this . expr = expr;
   }

   public String toString() {
   return "(return "+expr+")";
   }
}
class Assignment extends Statementc {

   protected Identifier lhs;
   protected Expression rhs;

   public Assignment () { }

   public Assignment (Identifier lhs, Expression rhs) {
     this . lhs = lhs;
     this . rhs = rhs;
   }

   public String toString () {
     return "(= " + lhs + " " + rhs + ")";
   }

}

class PrintStatement extends Statementc {

   //protected Identifier procId;
   protected Expression expr;

   public PrintStatement () { }

   public PrintStatement (Expression expr) {
     this . expr = expr;
   }

   public String toString () {
     return "(println " + expr + ")";
   }

}

class IfStatement extends Statementc {

  protected Expression test;
  protected Statementc thenStmt;
  protected Statementc elseStmt;

  public IfStatement () { }

  public IfStatement (Expression test, Statementc thenStmt, Statementc elseStmt) {
    this . test = test;
    this . thenStmt = thenStmt;
    this . elseStmt = elseStmt;
  }

  public String toString () {
     if(elseStmt == null)
     return "(if " + test + " " + thenStmt + ")";
     else return "(if "+test + " "+ thenStmt+ " "+ elseStmt+")";
   }

}

class WhileStatement extends Statementc {

  protected Expression test;
  protected Statementc body;

  public WhileStatement () { }

  public WhileStatement (Expression test, Statementc body) {
    this . test = test;
    this . body = body;
  }

  public String toString () {
     return "(while " + test + " " + body + ")";
   }

}

class Expression {

   protected Expression stmt1, stmt2;

   public Expression () { }

   public Expression (Expression stmt1, Expression stmt2) {
     this . stmt1 = stmt1;
     this . stmt2 = stmt2;
   }
   //System.out.println("Hellllllllllllll");
   public String toString () {
     if (stmt1 == null)
       return "";
     else if (stmt2 == null)
       return "" + stmt1;
     else
       return "" + stmt1 + "," + stmt2 + "";
   }

}

class Identifier extends Expression {

  protected String id;

  public Identifier () { }

  public Identifier (String id) {
    this . id = id;
  }

  public String toString () {
    return "(id " + id + ")";
  }

}

class IntValue extends Expression {

  protected int intValue;

  public IntValue () { }

  public IntValue (int intValue) {
    this . intValue = intValue;
  }

  public String toString () {
    return "(intValue " + intValue + ")";
  }

}

class Binary extends Expression {

  protected String op;
  protected Expression term1, term2;

  public Binary () { }

  public Binary (String op, Expression term1, Expression term2) {
    this . op = op;
    this . term1 = term1;
    this . term2 = term2;
  }

  public String toString () {
    return "(" + op + " " + term1 + " " + term2 + ")";
  }

}

class Call extends Expression {
  protected String op;
  protected Expression expr1;
  protected Expression expr2;

  public Call () { }
  public Call (String op, Expression expr1) {
	this . op = op;
  	this . expr1 = expr1;
  }
  public Call (String op){ 
	  this . op = op;
  }

  public String toString () {
    	if(expr1 == null)
	  return "("+op+")";
	else return "(apply "+op+" [ "+expr1+"])";	
  }

}
class Unary extends Expression {

  protected String op;
  protected Expression term;

  public Unary () { }

  public Unary (String op, Expression term) {
    this . op = op;
    this . term = term;
  }

  public String toString () {
    return "(" + op + " " + term + ")";
  }

}

