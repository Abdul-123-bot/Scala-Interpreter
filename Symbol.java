// Symbol enumeration definition
// Symbol is an enumeration to represent lexical token classes in the PL/0 
// programming language, described in Algorithms + Data Structures = Programs by
// Niklaus Wirth, Prentice-Hall, 1976.

public enum Symbol {
  EOF, 
  // punctuation
  PERIOD, COMMA, SEMICOLON ,LBRACES ,RBRACES,COLON,LBRACKET,RBRACKET,

  // operators
  ASSIGN, EQ, NE, LT, GT, LE, GE, PLUS, MINUS, TIMES, SLASH, LPAREN, RPAREN,POINT,LESSCOLON,LESSPERCENT,GREATCOLON,HASH,AT,DCOLON,DEQ,NOT,AND,DAND,OR,DOR,NOTEQ,ISEMPTY,HEAD,TAIL,
  // keywords
  ABSTRACT, BEGIN, CALL, CASE, CATCH, CLASS, CONST, DEF, DO, ELSE, END, EXTENDS, FALSE, FINAL, FINALLY, FOR, FORSOME, IF, IMPLICIT, IMPORT, LAZY, MACRO,NIL,LIST, MAIN, MATCH, NEW, NULL, OBJECT, ODD, OVERRIDE, PACKAGE, PRIVATE, PROC, PROTECTED, RETURN, SEALED, SUPER, THEN, THIS, THROW, TRAIT, TRUE, TRY, TYPE, VAL, VAR, WHILE, WITH, YIELD,PRINT,INT,ARGS,STRING,ARRAY,SCALA,IO,STDIN,READINT,

  // ids and integers
  ID, INTEGER,
}

