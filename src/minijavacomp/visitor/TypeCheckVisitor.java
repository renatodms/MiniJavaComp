package minijavacomp.visitor;

import minijavacomp.symboltable.SymbolTable;
import minijavacomp.visitor.exception.WrongTypeException;
import minijavacomp.ast.And;
import minijavacomp.ast.ArrayAssign;
import minijavacomp.ast.ArrayLength;
import minijavacomp.ast.ArrayLookup;
import minijavacomp.ast.Assign;
import minijavacomp.ast.Block;
import minijavacomp.ast.BooleanType;
import minijavacomp.ast.Call;
import minijavacomp.ast.ClassDeclExtends;
import minijavacomp.ast.ClassDeclSimple;
import minijavacomp.ast.Diff;
import minijavacomp.ast.Div;
import minijavacomp.ast.Eq;
import minijavacomp.ast.False;
import minijavacomp.ast.Formal;
import minijavacomp.ast.GreaterEq;
import minijavacomp.ast.GreaterThan;
import minijavacomp.ast.Identifier;
import minijavacomp.ast.IdentifierExp;
import minijavacomp.ast.IdentifierType;
import minijavacomp.ast.If;
import minijavacomp.ast.IntArrayType;
import minijavacomp.ast.IntegerLiteral;
import minijavacomp.ast.IntegerType;
import minijavacomp.ast.LessEq;
import minijavacomp.ast.LessThan;
import minijavacomp.ast.MainClass;
import minijavacomp.ast.MethodDecl;
import minijavacomp.ast.Minus;
import minijavacomp.ast.Mod;
import minijavacomp.ast.Mult;
import minijavacomp.ast.NewArray;
import minijavacomp.ast.NewObject;
import minijavacomp.ast.Not;
import minijavacomp.ast.Opposite;
import minijavacomp.ast.Or;
import minijavacomp.ast.Plus;
import minijavacomp.ast.Print;
import minijavacomp.ast.Program;
import minijavacomp.ast.This;
import minijavacomp.ast.Times;
import minijavacomp.ast.True;
import minijavacomp.ast.Type;
import minijavacomp.ast.VarDecl;
import minijavacomp.ast.While;
import minijavacomp.parser.ast.BooleanLiteral;

public class TypeCheckVisitor implements TypeVisitor {

	private SymbolTable symbolTable;

	public TypeCheckVisitor(SymbolTable st) {
		symbolTable = st;
	}

	// MainClass m;
	// ClassDeclList cl;
	public Type visit(Program n) {
		n.m.accept(this);
		for (int i = 0; i < n.cl.size(); i++) {
			n.cl.elementAt(i).accept(this);
		}
		return null;
	}

	// Identifier i1,i2;
	// Statement s;
	public Type visit(MainClass n) {
		n.i1.accept(this);
		n.i2.accept(this);
		n.s.accept(this);
		return null;
	}

	// Identifier i;
	// VarDeclList vl;
	// MethodDeclList ml;
	public Type visit(ClassDeclSimple n) {
		n.i.accept(this);
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.ml.size(); i++) {
			n.ml.elementAt(i).accept(this);
		}
		return null;
	}

	// Identifier i;
	// Identifier j;
	// VarDeclList vl;
	// MethodDeclList ml;
	public Type visit(ClassDeclExtends n) {
		n.i.accept(this);
		n.j.accept(this);
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.ml.size(); i++) {
			n.ml.elementAt(i).accept(this);
		}
		return null;
	}

	// Type t;
	// Identifier i;
	public Type visit(VarDecl n) {
		n.t.accept(this);
		n.i.accept(this);
		return null;
	}

	// Type t;
	// Identifier i;
	// FormalList fl;
	// VarDeclList vl;
	// StatementList sl;
	// Exp e;
	public Type visit(MethodDecl n) {
		n.t.accept(this);
		n.i.accept(this);
		for (int i = 0; i < n.fl.size(); i++) {
			n.fl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.sl.size(); i++) {
			n.sl.elementAt(i).accept(this);
		}
		n.e.accept(this);
		return null;
	}

	// Type t;
	// Identifier i;
	public Type visit(Formal n) {
		n.t.accept(this);
		n.i.accept(this);
		return null;
	}

	public Type visit(IntArrayType n) {
		return null;
	}

	public Type visit(BooleanType n) {
		return null;
	}

	public Type visit(IntegerType n) {
		return null;
	}

	// String s;
	public Type visit(IdentifierType n) {
		return null;
	}

	// StatementList sl;
	public Type visit(Block n) {
		for (int i = 0; i < n.sl.size(); i++) {
			n.sl.elementAt(i).accept(this);
		}
		return null;
	}

	// Exp e;
	// Statement s1,s2;
	public Type visit(If n) {
		n.e.accept(this);
		n.s1.accept(this);
		n.s2.accept(this);
		return null;
	}

	// Exp e;
	// Statement s;
	public Type visit(While n) {
		n.e.accept(this);
		n.s.accept(this);
		return null;
	}

	// Exp e;
	public Type visit(Print n) {
		n.e.accept(this);
		return null;
	}

	// Identifier i;
	// Exp e;
	public Type visit(Assign n) {
		n.i.accept(this);
		n.e.accept(this);
		return null;
	}

	// Identifier i;
	// Exp e1,e2;
	public Type visit(ArrayAssign n) {
		n.i.accept(this);
		n.e1.accept(this);
		n.e2.accept(this);
		return null;
	}

	// Exp e1,e2;
	public Type visit(And n) {
		n.e1.accept(this);
		n.e2.accept(this);
		return null;
	}

	// Exp e1,e2;
	public Type visit(LessThan n) {
		n.e1.accept(this);
		n.e2.accept(this);
		return null;
	}

	// Exp e1,e2;
	public Type visit(Plus n) {
		n.e1.accept(this);
		n.e2.accept(this);
		return null;
	}

	// Exp e1,e2;
	public Type visit(Minus n) {
		n.e1.accept(this);
		n.e2.accept(this);
		return null;
	}

	// Exp e1,e2;
	public Type visit(Times n) {
		n.e1.accept(this);
		n.e2.accept(this);
		return null;
	}

	// Exp e1,e2;
	public Type visit(ArrayLookup n) {
		n.e1.accept(this);
		n.e2.accept(this);
		return null;
	}

	// Exp e;
	public Type visit(ArrayLength n) {
		n.e.accept(this);
		return null;
	}

	// Exp e;
	// Identifier i;
	// ExpList el;
	public Type visit(Call n) {
		n.e.accept(this);
		n.i.accept(this);
		for (int i = 0; i < n.el.size(); i++) {
			n.el.elementAt(i).accept(this);
		}
		return null;
	}

	// int i;
	public Type visit(IntegerLiteral n) {
		return null;
	}

	public Type visit(True n) {
		return null;
	}

	public Type visit(False n) {
		return null;
	}

	// String s;
	public Type visit(IdentifierExp n) {
		return null;
	}

	public Type visit(This n) {
		return null;
	}

	// Exp e;
	public Type visit(NewArray n) {
		n.e.accept(this);
		return null;
	}

	// Identifier i;
	public Type visit(NewObject n) {
		return null;
	}

	// Exp e;
	public Type visit(Not n) {
		n.e.accept(this);
		return null;
	}

	// String s;
	public Type visit(Identifier n) {
		return null;
	}

	@Override
	public Type visit(Or n) {
		// TODO Auto-generated method stub
		
		Type type1 = n.e1.accept(this);
		Type type2 = n.e2.accept(this);
		
		if (!(type1 instanceof BooleanType)) new WrongTypeException().InfoWrongTypeException(new BooleanType(), type1);
		if (!(type2 instanceof BooleanType)) new WrongTypeException().InfoWrongTypeException(new BooleanType(), type2);
		
		return new BooleanType();
	}

	@Override
	public Type visit(Diff ns) {
		// TODO Auto-generated method stub
		
		Type type1 = ns.e1.accept(this);
		Type type2 = ns.e2.accept(this);
		
		if (!(type1 instanceof BooleanType)) new WrongTypeException().InfoWrongTypeException(new BooleanType(), type1);
		if (!(type2 instanceof BooleanType)) new WrongTypeException().InfoWrongTypeException(new BooleanType(), type2);
		
		return new BooleanType();
	}

	@Override
	public Type visit(LessEq lessEq) {
		// TODO Auto-generated method stub
		
		Type type1 = lessEq.e1.accept(this);
		Type type2 = lessEq.e2.accept(this);
		
		if (!(type1 instanceof BooleanType)) new WrongTypeException().InfoWrongTypeException(new BooleanType(), type1);
		if (!(type2 instanceof BooleanType)) new WrongTypeException().InfoWrongTypeException(new BooleanType(), type2);
		
		return new BooleanType();
	}

	@Override
	public Type visit(GreaterThan greaterThan) {
		// TODO Auto-generated method stub
		
		Type type1 = greaterThan.e1.accept(this);
		Type type2 = greaterThan.e2.accept(this);
		
		if (!(type1 instanceof BooleanType)) new WrongTypeException().InfoWrongTypeException(new BooleanType(), type1);
		if (!(type2 instanceof BooleanType)) new WrongTypeException().InfoWrongTypeException(new BooleanType(), type2);
		
		return new BooleanType();
	}

	@Override
	public Type visit(GreaterEq greaterEq) {
		// TODO Auto-generated method stub
		
		Type type1 = greaterEq.e1.accept(this);
		Type type2 = greaterEq.e2.accept(this);
		
		if (!(type1 instanceof BooleanType)) new WrongTypeException().InfoWrongTypeException(new BooleanType(), type1);
		if (!(type2 instanceof BooleanType)) new WrongTypeException().InfoWrongTypeException(new BooleanType(), type2);
		
		return new BooleanType();
	}

	@Override
	public Type visit(Eq eq) {
		// TODO Auto-generated method stub
		
		Type type1 = eq.e1.accept(this);
		Type type2 = eq.e2.accept(this);
		
		if (!(type1 instanceof BooleanType)) new WrongTypeException().InfoWrongTypeException(new BooleanType(), type1);
		if (!(type2 instanceof BooleanType)) new WrongTypeException().InfoWrongTypeException(new BooleanType(), type2);
		
		return new BooleanType();
	}

	@Override
	public Type visit(Mult mult) {
		// TODO Auto-generated method stub
		
		Type type1 = mult.e1.accept(this);
		Type type2 = mult.e2.accept(this);
		
		if (!(type1 instanceof IntegerType)) new WrongTypeException().InfoWrongTypeException(new IntegerType(), type1);
		if (!(type2 instanceof IntegerType)) new WrongTypeException().InfoWrongTypeException(new IntegerType(), type2);
		
		return new IntegerType();
	}

	@Override
	public Type visit(Div div) {
		// TODO Auto-generated method stub
		
		Type type1 = div.e1.accept(this);
		Type type2 = div.e2.accept(this);
		
		if (!(type1 instanceof IntegerType)) new WrongTypeException().InfoWrongTypeException(new IntegerType(), type1);
		if (!(type2 instanceof IntegerType)) new WrongTypeException().InfoWrongTypeException(new IntegerType(), type2);
		
		return new IntegerType();
	}

	@Override
	public Type visit(Mod mod) {
		// TODO Auto-generated method stub
		
		Type type1 = mod.e1.accept(this);
		Type type2 = mod.e2.accept(this);
		
		if (!(type1 instanceof IntegerType)) new WrongTypeException().InfoWrongTypeException(new IntegerType(), type1);
		if (!(type2 instanceof IntegerType)) new WrongTypeException().InfoWrongTypeException(new IntegerType(), type2);
		
		return new IntegerType();
	}

	@Override
	public Type visit(Opposite opposite) {
		// TODO Auto-generated method stub
		
		Type type = opposite.e1.accept(this);
		
		if(!(type instanceof BooleanType)) new WrongTypeException().InfoWrongTypeException(new BooleanType(), type);
		
		return new BooleanType();
	}

	@Override
	public Type visit(BooleanLiteral booleanLiteral) {
		// TODO Auto-generated method stub
		return new BooleanType();
	}
}
