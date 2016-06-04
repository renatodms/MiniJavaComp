package minijavacomp.ast;

import minijavacomp.visitor.Visitor;

public class Argument {
    private Type type;
    private Identifier name;

    public Argument(Type type, Identifier name) {
        this.type = type;
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public Identifier getName() {
        return name;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visit(this);
    }
}