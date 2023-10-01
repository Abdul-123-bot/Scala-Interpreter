// Token class definition
// Token is a class to represent lexical tokens in the in the PL/0 programming 
// language, described in Algorithms + Data Structures = Programs by
// Niklaus Wirth, Prentice-Hall, 1976.

public class Token {

  private Symbol symbol;	// current token
  private String lexeme;	// lexeme

  public Token () { }

  public Token (Symbol symbol) {
    this (symbol, null);
  }

  public Token (Symbol symbol, String lexeme) {
    this . symbol = symbol;
    this . lexeme  = lexeme;
  }

  public Symbol symbol () { return symbol; }

  public String lexeme () { return lexeme; }

  public String toString () {
    switch (symbol) {
      case PERIOD :    return "(punctuation, .) ";
      case COMMA :     return "(punctuation, ,) ";
      case SEMICOLON : return "(punctuation, ;) ";
      case LBRACES :   return "(punctuation, {) ";
      case RBRACES :   return "(punctuation, }) ";
      case COLON:      return "(punctuation,:) ";
      case LBRACKET:   return "(punctuation,[)";
      case RBRACKET:   return "(punctuation,])";
      case ASSIGN :    return "(operator, :=) ";
      case DCOLON :    return "(operator, ::) ";
      case EQ :        return "(operator, =) ";
      case DEQ :       return "(operator, ==) ";
      case NE :        return "(operator, <>) ";
      case LT :        return "(operator, <) ";
      case GT :        return "(operator, >) ";
      case LE :        return "(operator, <=) ";
      case GE :        return "(operator, >=) ";
      case PLUS :      return "(operator, +) ";
      case MINUS :     return "(operator, -) ";
      case TIMES :     return "(operator, *) ";
      case SLASH :     return "(operator, /) ";
      case LPAREN :    return "(operator, () ";
      case RPAREN :    return "(operator, )) ";
      case POINT:      return "(operator,<-) ";
      case LESSCOLON:  return "(operator,<:) ";
      case LESSPERCENT:return "(operator,<%) ";
      case GREATCOLON: return "(operator,>:) ";
      case HASH:       return "(operator,#) ";
      case AT:         return "(operator,@) ";
      case NOT:        return "(operator,!) ";
      case AND:        return "(operator,&) ";
      case DAND:       return "(operator,&&) ";
      case OR:         return "(operator,|)";
      case DOR:        return "(operator,||)";
      case NOTEQ:      return "(operator,!=)";
      case ISEMPTY:    return "(operator,isEmpty)";
      case TAIL:       return "(operator,tail)";
      case NIL:        return "(keyword, Nil) ";
      case HEAD:       return "(operator, head)";
      case SCALA:      return"(keyword,scala)";
      case IO:         return"(keyword,io)";
      case STDIN:      return"(keyword,StdIn)";
      case READINT:    return "(keyword,readInt)";
      case LIST:       return "(keyword, List)";
      case ARGS:       return "(keyword, args)";
      case STRING:     return "(keyword, String)";
      case ARRAY:      return "(keyword, Array)";		       
      case BEGIN :     return "(keyword, begin) ";
      case CALL :      return "(keyword, call) ";
      case CONST :     return "(keyword, const) ";
      case DO :        return "(keyword, do) ";
      case END :       return "(keyword, end) ";
      case IF :        return "(keyword, if) ";
      case ODD :       return "(keyword, odd) ";
      case PROC :      return "(keyword, proc) ";
      case THEN :      return "(keyword, then) ";
      case VAR :       return "(keyword, var) ";
      case WHILE :     return "(keyword, while) ";
      case INT :       return "(keyword, Int)";
      case PRINT :     return "(keyword, println)";
      case ID :        return lexeme;
      case INTEGER :   return "(integer, " + lexeme + ") ";
      case ABSTRACT: return "(keyword, abstract) "; 
      case CASE: return "(keyword, case) ";
      case CATCH: return "(keyword, catch) ";
      case CLASS: return "(keyword, class) ";
      case DEF: return "(keyword, def) ";
      case ELSE: return "(keyword, else) ";
      case EXTENDS: return "(keyword, extends) ";
      case FALSE: return "(keyword, false) ";
      case FINAL: return "(keyword, final) ";
      case FINALLY: return "(keyword, finally) ";
      case FOR: return "(keyword, for) ";
      case FORSOME: return "(keyword, forsome) ";
      case IMPLICIT: return "(keyword, implicit) ";
      case IMPORT: return "(keyword, import) ";
      case LAZY: return "(keyword, lazy) ";
      case MACRO: return "(keyword, macro) ";
      case MAIN: return "(keyword, main) ";
      case MATCH: return "(keyword, match) ";
      case NEW: return "(keyword, new) ";
      case NULL: return "(keyword, null) ";
      case OBJECT: return "(keyword, object) ";
      case OVERRIDE: return "(keyword, override) ";
      case PACKAGE: return "(keyword, package) ";
      case PRIVATE: return "(keyword, private) ";
      case PROTECTED: return "(keyword, protected) ";
      case RETURN: return "(keyword, return) ";
      case SEALED: return "(keyword, sealed) ";
      case SUPER: return "(keyword, super) ";
      case THIS: return "(keyword, this) ";
      case THROW: return "(keyword, throw) ";
      case TRAIT: return "(keyword, trait) ";
      case TRUE: return "(keyword, true) ";
      case TRY: return "(keyword, try) ";
      case TYPE: return "(keyword, type) ";
      case VAL: return "(keyword, val) ";
      case WITH: return "(keyword, with) ";
      case YIELD: return "(keyword, yield) ";
      default : 
	ErrorMessage . print (0, "Unrecognized token");
        return null;
    }
  }

}
