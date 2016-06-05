package minijavacomp.visitor.exception;

import minijavacomp.ast.Type;

public class WrongTypeException {
	public static void InfoWrongTypeException (Type expected, Type got) {
		System.out.println("Expected "+expected.toString() + " type, but got "+ got.toString());
	}
}
