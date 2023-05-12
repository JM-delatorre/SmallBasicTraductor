grammar MiLenguaje;

program       : statement*;
statement     : assignment | forLoop | whileLoop | if_statement | subroutine_call | subroutine | tag | gotorule | builtIn;
assignment    : variable assign expRule;
stepForLoop   : 'Step' expRule;
toForLoop    : 'To' expRule stepForLoop?;
forLoop       : 'For' variable forParameters toForLoop statement+ 'EndFor';
forParameters : '=' expRule;
whileLoop    : 'While' conditionalParams statement+ 'EndWhile';
conditionalParams: expRule;
if_statement : 'If' conditionalParams 'Then' statement+ elseif_statement* else_statement? 'EndIf';
elseif_statement: 'ElseIf' conditionalParams 'Then' statement+;
else_statement : 'Else' statement+;
subroutine   : 'Sub' ID statement+ 'EndSub';
subroutine_call : ID '(' ')';
// hacer
argument_list : expRule? (',' expRule?)*;
expRule       : '(' expRule ')' | expRule op expRule | BOOL | number | string | variable | subroutine_call | '-' expRule | builtIn;
variable      : ID ('[' expRule ']')*;
builtIn_name  : 'Array'| 'Stack' | 'Program' | 'TextWindow' ;
builtIn       : builtIn_name '.' ID '(' argument_list ')' ;
tag           : ID':';
gotorule      : 'Goto' ID;
op            : ('+' | '-' | '*' | '/' | '=' | '<>' | '<' | '>' | '<=' | '>=' | 'And' | 'Or' );
number        : (INT | FLOAT);
string        : STRING;
assign: '=';

ID            : [a-zA-Z\u00c0-\u00ff][a-zA-Z\u00c0-\u00ff0-9_]*;
BOOL          : '"'([Ff][aA][lL][sS][eE] | [Tt][rR][uU][eE])'"';
INT           : [0-9]+;
FLOAT         : [0-9]+ '.' [0-9]+;
STRING        : '"' (~'"')* '"';
COMMENT       : '\''~[\r\n]* -> skip;
WS            : [ \t\n\r]+ -> skip;