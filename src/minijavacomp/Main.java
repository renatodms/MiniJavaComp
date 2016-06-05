package minijavacomp;

import java.nio.file.Paths;

import minijavacomp.ast.Program;
import minijavacomp.parser.parser.Parser;
import minijavacomp.visitor.BuildSymbolTableVisitor;
import minijavacomp.visitor.TypeCheckVisitor;

public class Main {
	public static void main (String[] args) throws Exception{
		Parser p = new Parser();
		//programa na forma de AST
		Program prog = (Program)p.parse().value;
		BuildSymbolTableVisitor stVis = new BuildSymbolTableVisitor();
		//construindo tabela de símbolos
		prog.accept(stVis); 
		//fazendo a checagem de tipos
		prog.accept(new TypeCheckVisitor(stVis.getSymbolTable()));
	}
}
