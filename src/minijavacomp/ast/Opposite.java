package minijavacomp.ast;

import minijavacomp.visitor.TypeVisitor;
import minijavacomp.visitor.Visitor;

public class Opposite extends Exp {

	public Exp e1;
	  
	  public Opposite(Exp ae1) {
	    e1=ae1;
	  }

	  public void accept(Visitor v) {
	    v.visit(this);
	  }

	  public Type accept(TypeVisitor v) {
	    return v.visit(this);
	  }

}
