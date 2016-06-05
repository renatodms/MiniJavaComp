import java.nio.file.Paths;

import ast.Program;
import parser.Parser;

public class Main {
	public static void main (String[] args) throws Exception{
		for (int j = 0; j < args.length; j++) {
			Program binarySearhProgram = (Program) new Parser(Paths.get(args[j])).parse().value;
		}
		 
	}
}
