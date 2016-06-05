package minijavacomp.ast;
import minijavacomp.visitor.Visitor;
import minijavacomp.visitor.TypeVisitor;

public class IntegerType extends Type {
  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
}
