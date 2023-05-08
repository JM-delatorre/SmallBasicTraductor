grammar MiLenguaje;

program       : statement*;
statement     : assignment | forLoop | whileLoop | if_statement | subroutine_call | subroutine | tag | goto | builtIn;
assignment    : variable '=' expression;
forLoop          : 'For' variable '=' expression 'To' expression ( 'Step' expression )?  statement+ 'EndFor';
whileLoop    : 'While' expression statement+ 'EndWhile';
if_statement : 'If' expression 'Then' statement+ ( 'ElseIf' expression 'Then' statement+ )* ('Else' statement+ )? 'EndIf';
subroutine   : 'Sub' ID statement+ 'EndSub';
subroutine_call : ID '(' ')';

argument_list : expression? (',' expression?)*;
expression    : '(' expression ')' | expression op expression | BOOL | number | string | variable | subroutine_call | '-' expression | builtIn;
variable      : ID ('[' expression ']')*;
number        : (INT | FLOAT);
string        : STRING;
builtIn       : ('Array'| 'Stack' | 'Program' | 'TextWindow') '.' ID '(' argument_list ')' ;
tag           : ID':';
goto          : 'Goto' ID;
op            : ('+' | '-' | '*' | '/' | '=' | '<>' | '<' | '>' | '<=' | '>=' | 'And' | 'Or' );

ID            : [a-zA-ZÀ-ÿ][a-zA-ZÀ-ÿ0-9_]*;
BOOL          : '"'([Ff][aA][lL][sS][eE] | [Tt][rR][uU][eE])'"';
INT           : [0-9]+;
FLOAT         : [0-9]+ '.' [0-9]+;
STRING        : '"' (~'"')* '"';
COMMENT       :  '\''~[\r\n]* -> skip;
WS            : [ \t\n\r]+ -> skip;