grammar SimpleExp;

@lexer::members {
    int lexicalErrors=0;
}

// PARSER RULES

prog: exp EOF {System.out.println("Parsing finished!");};

exp: exp TIMES exp #expProd1 | exp PLUS exp #expProd2 | LPAR exp RPAR #expProd3 | NUM #expProd4;
/* <assoc=right> */

// LEXER RULES

PLUS    : '+';
TIMES   : '*';
LPAR    : '(';
RPAR    : ')';
NUM     : '0' | ('1'..'9') ('0'..'9')* ;

WHITESP : (' ' | '\t' | '\n' | '\r')+ -> channel(HIDDEN);
COMMENT : ('/*' .*? '*/') -> channel(HIDDEN);  /* .* questa stella di kleene è di massimal match, quindi prende la più lunga (greedy), col punto di domanda disabilito il massimal match e prendo la prima che arriva quindi in generale la pià corta */

ERR     : . {System.out.println("Invalid char: " + getText()); lexicalErrors++;} -> channel(HIDDEN);
