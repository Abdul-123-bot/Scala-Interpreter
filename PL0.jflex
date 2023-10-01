%%
%{
  private void echo () { System . out . print (yytext ()); }

  public int position () { return yycolumn; }
%}

%class    PL0Lexer
%function nextToken
%type	  Token
%unicode
%line
%column
%eofval{
  { return new Token (Symbol . EOF); }
%eofval}

WhiteSpace = [ \t\n]
Ident      = [:jletter:] ([:jletter:] | [:digit:])*
Integer    = [:digit:] [:digit:]*
InChar     = [^\r\n]
comment    = [/][*][^*]*[*]+([^*/][^*]*[*]+)*[/]
incomment  = "//".*   
%%
"."  	    	{ echo (); return new Token (Symbol . PERIOD); }
","		{ echo (); return new Token (Symbol . COMMA); }
";"		{ echo (); return new Token (Symbol . SEMICOLON); }
"{"             { echo (); return new Token (Symbol . LBRACES); }
"}"             { echo (); return new Token (Symbol . RBRACES); }
":"             { echo(); return new Token(Symbol . COLON); }
"::"            { echo(); return new Token(Symbol . DCOLON); }
"["             { echo(); return new Token(Symbol . LBRACKET); }
"]"             { echo(); return new Token(Symbol . RBRACKET); }
":="     	{ echo (); return new Token (Symbol . ASSIGN); }
"="     	{ echo (); return new Token (Symbol . EQ); }
"=="            {echo (); return new Token (Symbol . DEQ); }
"<>"    	{ echo (); return new Token (Symbol . NE); }
"<"     	{ echo (); return new Token (Symbol . LT); }
">"     	{ echo (); return new Token (Symbol . GT); }
"<="    	{ echo (); return new Token (Symbol . LE); }
">="    	{ echo (); return new Token (Symbol . GE); }
"+"		{ echo (); return new Token (Symbol . PLUS); }
"-"		{ echo (); return new Token (Symbol . MINUS); }
"*"     	{ echo (); return new Token (Symbol . TIMES); }
"/"  	 	{ echo (); return new Token (Symbol . SLASH); }
"("		{ echo (); return new Token (Symbol . LPAREN); }
")"		{ echo (); return new Token (Symbol . RPAREN); }          
"<-"            { echo(); return new Token(Symbol . POINT); }
"<:"            { echo(); return new Token(Symbol . LESSCOLON); }
"<%"            { echo(); return new Token(Symbol . LESSPERCENT); }
">:"            { echo(); return new Token(Symbol . GREATCOLON); }
"#"             { echo(); return new Token(Symbol . HASH);}
"@"             { echo(); return new Token(Symbol . AT); }
"!"             { echo(); return new Token(Symbol . NOT);}
"!="            { echo(); return new Token(Symbol . NOTEQ);}
"&"             { echo (); return new Token(Symbol . AND);} 
"&&"            { echo (); return new Token(Symbol . DAND);}
"|"             { echo (); return new Token(Symbol . OR);}
"||"            { echo (); return new Token(Symbol . DOR);}
"isEmpty"       { echo (); return new Token(Symbol . ISEMPTY); }
"tail"          { echo (); return new Token(Symbol . TAIL); }
Nil             { echo (); return new Token(Symbol . NIL); }
"head"          { echo (); return new Token(Symbol . HEAD);}
List            { echo (); return new Token(Symbol . LIST);}
scala           { echo (); return new Token(Symbol . SCALA);}
io              { echo (); return new Token(Symbol . IO);}
StdIn           { echo (); return new Token(Symbol . STDIN);}
readInt         { echo (); return new Token(Symbol . READINT);}
args            { echo(); return new Token(Symbol . ARGS); }
String          { echo(); return new Token(Symbol . STRING); }
Array           { echo(); return new Token(Symbol . ARRAY); }
begin 		{ echo (); return new Token (Symbol . BEGIN); }
call    	{ echo (); return new Token (Symbol . CALL); }
const   	{ echo (); return new Token (Symbol . CONST); }
do      	{ echo (); return new Token (Symbol . DO); }
end     	{ echo (); return new Token (Symbol . END); }
if		{ echo (); return new Token (Symbol . IF); }
odd     	{ echo (); return new Token (Symbol . ODD); }
procedure  	{ echo (); return new Token (Symbol . PROC); }
then    	{ echo (); return new Token (Symbol . THEN); }
Int             { echo (); return new Token (Symbol . INT); }
println         { echo (); return new Token (Symbol . PRINT); }
var  	 	{ echo (); return new Token (Symbol . VAR); }
while		{ echo (); return new Token (Symbol . WHILE); }
abstract        { echo (); return new Token (Symbol . ABSTRACT);}
case            { echo (); return new Token (Symbol . CASE);}
catch           { echo (); return new Token (Symbol . CATCH);}
class           { echo (); return new Token (Symbol . CLASS);}
def             { echo (); return new Token (Symbol . DEF);}
else            { echo (); return new Token (Symbol . ELSE);}
extends         { echo (); return new Token (Symbol . EXTENDS);}
false           { echo (); return new Token (Symbol . FALSE);}
final           { echo (); return new Token (Symbol . FINAL);}
finally         { echo (); return new Token (Symbol . FINALLY);}
for             { echo (); return new Token (Symbol . FOR);}
forsome         { echo (); return new Token (Symbol . FORSOME);}
implicit        { echo (); return new Token (Symbol . IMPLICIT);}
import          { echo (); return new Token (Symbol . IMPORT);}
lazy            { echo (); return new Token (Symbol . LAZY);}
macro           { echo (); return new Token (Symbol . MACRO);}
main            { echo (); return new Token (Symbol . MAIN);}
match           { echo (); return new Token (Symbol . MATCH);}
new             { echo (); return new Token (Symbol . NEW);}
null            { echo (); return new Token (Symbol . NULL);}
object          { echo (); return new Token (Symbol . OBJECT);}
override        { echo (); return new Token (Symbol . OVERRIDE);}
package         { echo (); return new Token (Symbol . PACKAGE);}
private         { echo (); return new Token (Symbol . PRIVATE);}
protected       { echo (); return new Token (Symbol . PROTECTED);}
return          { echo (); return new Token (Symbol . RETURN);}
sealed          { echo (); return new Token (Symbol . SEALED);}
super           { echo (); return new Token (Symbol . SUPER);}
this            { echo (); return new Token (Symbol . THIS);}
throw           { echo (); return new Token (Symbol . THROW);}
trait           { echo (); return new Token (Symbol . TRAIT);}
true            { echo (); return new Token (Symbol . TRUE);}
try             { echo (); return new Token (Symbol . TRY);}
type            { echo (); return new Token (Symbol . TYPE);}
val             { echo (); return new Token (Symbol . VAL);}
with            { echo (); return new Token (Symbol . WITH);}
yield           { echo (); return new Token (Symbol . YIELD);}
{Ident}   	{ echo (); return new Token (Symbol . ID, yytext ()); }
{Integer}	{ echo (); return new Token (Symbol . INTEGER, yytext ()); }
{WhiteSpace}	{ echo (); }
{comment}       { echo (); }
{incomment}     { echo (); }
.		{ echo (); ErrorMessage . print (yycolumn, "Illegal character"); }
