package minijavacomp.ast;
import minijavacomp.visitor.Visitor;
import minijavacomp.visitor.TypeVisitor;

public abstract class Statement {
  public abstract void accept(Visitor v);
  public abstract Type accept(TypeVisitor v);
}
