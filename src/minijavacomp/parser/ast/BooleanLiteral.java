package minijavacomp.parser.ast;

import minijavacomp.ast.Exp;
import minijavacomp.ast.Type;
import minijavacomp.visitor.TypeVisitor;
import minijavacomp.visitor.Visitor;

public class BooleanLiteral extends Exp {

	public boolean e1;
	  
	  public BooleanLiteral(Boolean v) {
	    e1=v;
	  }

	  public void accept(Visitor v) {
	    v.visit(this);
	  }

	  public Type accept(TypeVisitor v) {
	    return v.visit(this);
	  }

}
