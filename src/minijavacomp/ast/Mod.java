package ast;

import visitor.TypeVisitor;
import visitor.Visitor;

public class Mod extends Exp {

	public Exp e1,e2;
	  
	  public Mod(Exp ae1, Exp ae2) {
	    e1=ae1; e2=ae2;
	  }

	  public void accept(Visitor v) {
	    v.visit(this);
	  }

	  public Type accept(TypeVisitor v) {
	    return v.visit(this);
	  }

}