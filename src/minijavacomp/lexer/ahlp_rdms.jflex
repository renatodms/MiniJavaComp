package lexer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import parser.sym;
import java_cup.runtime.ComplexSymbolFactory;

%%

%class Lexer
%cup
%public
%unicode
%line
%column

%{
    private ComplexSymbolFactory factory;

    public Lexer(Path file, ComplexSymbolFactory factory) throws IOException {
	  this(Files.newBufferedReader(file));
      this.factory = factory;
  }
%}

%eofval{
    return factory.newSymbol("EOF", sym.EOF);
%eofval}

endLine = \r|\n|\r\n
space = [ \t\f]
commentLine = "//"[^\n]*[\n]
comment = "/*"[^"*/"]*"*/"
nonToken = {endLine} | {comment} | {commentLine} | {space}



%%

{nonToken}
{  }

boolean {return factory.newSymbol(yytext(), sym.BOOLEAN);}
class {return factory.newSymbol(yytext(), sym.CLASS);}
public {return factory.newSymbol(yytext(), sym.PUBLIC);}
extends {return factory.newSymbol(yytext(), sym.EXTENDS);}
static {return factory.newSymbol(yytext(), sym.STATIC);}
void {return factory.newSymbol(yytext(), sym.VOID);}
main {return factory.newSymbol(yytext(), sym.MAIN);}
int {return factory.newSymbol(yytext(), sym.INT);}
while {return factory.newSymbol(yytext(), sym.WHILE);}
if {return factory.newSymbol(yytext(), sym.IF);}
else {return factory.newSymbol(yytext(), sym.ELSE);}
return {return factory.newSymbol(yytext(), sym.RETURN);}
length {return factory.newSymbol(yytext(), sym.LENGTH);}
true {return factory.newSymbol(yytext(), sym.TRUE);}
false {return factory.newSymbol(yytext(), sym.FALSE);}
this {return factory.newSymbol(yytext(), sym.THIS);}
new {return factory.newSymbol(yytext(), sym.NEW);}
"System.out.println" {return factory.newSymbol(yytext(), sym.PRINTLN);}

"&&" {return factory.newSymbol(yytext(), sym.AND);}
"<" {return factory.newSymbol(yytext(), sym.LESS);}
">" {return factory.newSymbol(yytext(), sym.GREATER);}
"<=" {return factory.newSymbol(yytext(), sym.LESS_EQ);}
">=" {return factory.newSymbol(yytext(), sym.GREATER_EQ);}
"==" {return factory.newSymbol(yytext(), sym.EQUALS);}
"!=" {return factory.newSymbol(yytext(), sym.DIFF);}
"+" {return factory.newSymbol(yytext(), sym.PLUS);}
"-" {return factory.newSymbol(yytext(), sym.MINUS);}
"*" {return factory.newSymbol(yytext(), sym.TIMES);}
"!" {return factory.newSymbol(yytext(), sym.OPP);}

";" {return factory.newSymbol(yytext(), sym.SEMICOLON);}
"." {return factory.newSymbol(yytext(), sym.DOT);}
"," {return factory.newSymbol(yytext(), sym.COMMA);}
"=" {return factory.newSymbol(yytext(), sym.ASSIGN);}
"(" {return factory.newSymbol(yytext(), sym.O_PAR);}
")" {return factory.newSymbol(yytext(), sym.C_PAR);}
"{" {return factory.newSymbol(yytext(), sym.O_BRAC);}
"}" {return factory.newSymbol(yytext(), sym.C_BRAC);}
"[" {return factory.newSymbol(yytext(), sym.O_BRACK);}
"]" {return factory.newSymbol(yytext(), sym.C_BRACK);}

[a-zA-Z_][a-zA-Z0-9_]* {return factory.newSymbol(yytext(), sym.ID);}

[0-9]+ {return factory.newSymbol(yytext(), sym.INTEGER);}

. {throw new RuntimeException("Caractere ilegal! \'"+yytext()+"\' na linha: "+yyline+", coluna: "+yycolumn);}
