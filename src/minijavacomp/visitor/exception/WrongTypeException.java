package minijavacomp.visitor.exception;

import minijavacomp.ast.Type;

public class WrongTypeException extends Exception {
	public WrongTypeException (Type expected, Type got) {
		super("Expected "+expected.toString() + " type, but got "+ got.toString());
	}
}
