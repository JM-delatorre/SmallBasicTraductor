grammar MiLenguaje;

program       : statement*;
statement     : assignment | forLoop | whileLoop | if_statement | subroutine_call | subroutine | tag | gotorule | builtIn;
assignment    : variable '=' expRule;
forLoop       : 'For' variable '=' expRule 'To' expRule ( 'Step' expRule )?  statement+ 'EndFor';
whileLoop    : 'While' expRule statement+ 'EndWhile';
if_statement : 'If' expRule 'Then' statement+ ( 'ElseIf' expRule 'Then' statement+ )* ('Else' statement+ )? 'EndIf';
subroutine   : 'Sub' ID statement+ 'EndSub';
subroutine_call : ID '(' ')';

argument_list : expRule? (',' expRule?)*;
expRule       : '(' expRule ')' | expRule op expRule | BOOL | number | string | variable | subroutine_call | '-' expRule | builtIn;
variable      : ID ('[' expRule ']')*;
number        : (INT | FLOAT);
string        : STRING;
builtIn       : ('Array'| 'Stack' | 'Program' | 'TextWindow') '.' ID '(' argument_list ')' ;
tag           : ID':';
gotorule      : 'Goto' ID;
op            : ('+' | '-' | '*' | '/' | '=' | '<>' | '<' | '>' | '<=' | '>=' | 'And' | 'Or' );

ID            : [a-zA-ZÀ-ÿ][a-zA-ZÀ-ÿ0-9_]*;
BOOL          : '"'([Ff][aA][lL][sS][eE] | [Tt][rR][uU][eE])'"';
INT           : [0-9]+;
FLOAT         : [0-9]+ '.' [0-9]+;
STRING        : '"' (~'"')* '"';
COMMENT       : '\''~[\r\n]* -> skip;
WS            : [ \t\n\r]+ -> skip;