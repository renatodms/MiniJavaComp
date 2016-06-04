%%

%public
%standalone
%class rdms

%%

[\r|\n|\r\n] | [ \t] {/*ws*/}
"/*"[^"*/"]*"*/" {System.out.println(yytext());}
"//"[^\n]*[\n] {System.out.println(yytext());}

boolean {System.out.println(yytext());}
class {System.out.println(yytext());}
public {System.out.println(yytext());}
extends {System.out.println(yytext());}
static {System.out.println(yytext());}
void {System.out.println(yytext());}
main {System.out.println(yytext());}
String {System.out.println(yytext());}
int {System.out.println(yytext());}
while {System.out.println(yytext());}
if {System.out.println(yytext());}
else {System.out.println(yytext());}
return {System.out.println(yytext());}
length {System.out.println(yytext());}
true {System.out.println(yytext());}
false {System.out.println(yytext());}
this {System.out.println(yytext());}
new {System.out.println(yytext());}
"System.out.println" {System.out.println(yytext());}

"&&" {System.out.println(yytext());}
"<" {System.out.println(yytext());}
"==" {System.out.println(yytext());}
"!=" {System.out.println(yytext());}
"+" {System.out.println(yytext());}
"-" {System.out.println(yytext());}
"*" {System.out.println(yytext());}
"!" {System.out.println(yytext());}

";" {System.out.println(yytext());}
"." {System.out.println(yytext());}
"," {System.out.println(yytext());}
"=" {System.out.println(yytext());}
"(" {System.out.println(yytext());}
")" {System.out.println(yytext());}
"{" {System.out.println(yytext());}
"}" {System.out.println(yytext());}
"[" {System.out.println(yytext());}
"]" {System.out.println(yytext());}

[a-zA-Z_][a-zA-Z0-9_]* {System.out.println(yytext());}

[0-9]+ {System.out.println(yytext());}

. {throw new RuntimeException("Caractere ilegal! \'"+yytext()+"\' na linha: "+yyline+", coluna: "+yycolumn);}