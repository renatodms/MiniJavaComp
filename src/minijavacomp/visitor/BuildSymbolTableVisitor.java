package minijavacomp.visitor;

import minijavacomp.symboltable.SymbolTable;
import minijavacomp.symboltable.Class;
import minijavacomp.symboltable.Method;
import minijavacomp.symboltable.Variable;
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
import minijavacomp.ast.VarDecl;
import minijavacomp.ast.While;
import minijavacomp.parser.ast.BooleanLiteral;

public class BuildSymbolTableVisitor implements Visitor {

	SymbolTable symbolTable;

	public BuildSymbolTableVisitor() {
		symbolTable = new SymbolTable();
	}

	public SymbolTable getSymbolTable() {
		return symbolTable;
	}

	private Class currClass;
	private Method currMethod;

	// MainClass m;
	// ClassDeclList cl;
	public void visit(Program n) {
		n.m.accept(this);
		
		symbolTable.addClass(n.m.i1.toString(), null);
		currClass = new Class(n.m.i1.toString(), null);
		//visit(n.m);
		
		for (int i = 0; i < n.cl.size(); i++) {
			n.cl.elementAt(i).accept(this);
		}
	}

	// Identifier i1,i2;
	// Statement s;
	public void visit(MainClass n) {
		n.i1.accept(this);
		n.i2.accept(this);
		n.s.accept(this);
		
		symbolTable.addClass(n.i1.toString(), null);
		currClass = new Class(n.i1.toString(), null);
		
	}

	// Identifier i;
	// VarDeclList vl;
	// MethodDeclList ml;
	public void visit(ClassDeclSimple n) {
		n.i.accept(this);
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);
			
			currClass.addVar(n.i.toString(), n.vl.elementAt(i).t);
			
		}
		for (int i = 0; i < n.ml.size(); i++) {
			n.ml.elementAt(i).accept(this);
			
			currClass.addMethod(n.ml.elementAt(i).i.toString(), n.ml.elementAt(i).t);
			currMethod = new Method(n.ml.elementAt(i).i.toString(), n.ml.elementAt(i).t);
			//visit(n.ml.elementAt(i));
			
		}
	}

	// Identifier i;
	// Identifier j;
	// VarDeclList vl;
	// MethodDeclList ml;
	public void visit(ClassDeclExtends n) {
		n.i.accept(this);
		n.j.accept(this);
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);
			
			currClass.addVar(n.i.toString(), n.vl.elementAt(i).t);
			currClass.addVar(n.j.toString(), n.vl.elementAt(i).t);
			
		}
		for (int i = 0; i < n.ml.size(); i++) {
			n.ml.elementAt(i).accept(this);
			
			currClass.addMethod(n.ml.elementAt(i).i.toString(), n.ml.elementAt(i).t);
			currMethod = new Method(n.ml.elementAt(i).i.toString(), n.ml.elementAt(i).t);
			//visit(n.ml.elementAt(i));
			
		}
	}

	// Type t;
	// Identifier i;
	public void visit(VarDecl n) {
		n.t.accept(this);
		n.i.accept(this);
		
		currMethod.addVar(n.i.toString(), n.t);
		
	}

	// Type t;
	// Identifier i;
	// FormalList fl;
	// VarDeclList vl;
	// StatementList sl;
	// Exp e;
	public void visit(MethodDecl n) {
		n.t.accept(this);
		n.i.accept(this);
		
		currMethod.addVar(n.i.toString(), n.t);
		
		for (int i = 0; i < n.fl.size(); i++) {
			n.fl.elementAt(i).accept(this);
			
			//visit(n.fl.elementAt(i));
			
		}
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);
			
			currMethod.addVar(n.vl.elementAt(i).i.toString(), n.vl.elementAt(i).t);
			
		}
		for (int i = 0; i < n.sl.size(); i++) {
			n.sl.elementAt(i).accept(this);
			
			//visit(n.sl.elementAt(i));
			
		}
		n.e.accept(this);
		
		//visit(n.e);
		
	}

	// Type t;
	// Identifier i;
	public void visit(Formal n) {
		n.t.accept(this);
		n.i.accept(this);
		
		currMethod.addVar(n.i.toString(), n.t);
		
	}

	public void visit(IntArrayType n) {
	}

	public void visit(BooleanType n) {
	}

	public void visit(IntegerType n) {
	}

	// String s;
	public void visit(IdentifierType n) {
	}

	// StatementList sl;
	public void visit(Block n) {
		for (int i = 0; i < n.sl.size(); i++) {
			n.sl.elementAt(i).accept(this);
		}
	}

	// Exp e;
	// Statement s1,s2;
	public void visit(If n) {
		n.e.accept(this);
		n.s1.accept(this);
		n.s2.accept(this);
	}

	// Exp e;
	// Statement s;
	public void visit(While n) {
		n.e.accept(this);
		n.s.accept(this);
	}

	// Exp e;
	public void visit(Print n) {
		n.e.accept(this);
	}

	// Identifier i;
	// Exp e;
	public void visit(Assign n) {
		n.i.accept(this);
		n.e.accept(this);
	}

	// Identifier i;
	// Exp e1,e2;
	public void visit(ArrayAssign n) {
		n.i.accept(this);
		n.e1.accept(this);
		n.e2.accept(this);
	}

	// Exp e1,e2;
	public void visit(And n) {
		n.e1.accept(this);
		n.e2.accept(this);
	}

	// Exp e1,e2;
	public void visit(LessThan n) {
		n.e1.accept(this);
		n.e2.accept(this);
	}

	// Exp e1,e2;
	public void visit(Plus n) {
		n.e1.accept(this);
		n.e2.accept(this);
	}

	// Exp e1,e2;
	public void visit(Minus n) {
		n.e1.accept(this);
		n.e2.accept(this);
	}

	// Exp e1,e2;
	public void visit(Times n) {
		n.e1.accept(this);
		n.e2.accept(this);
	}

	// Exp e1,e2;
	public void visit(ArrayLookup n) {
		n.e1.accept(this);
		n.e2.accept(this);
	}

	// Exp e;
	public void visit(ArrayLength n) {
		n.e.accept(this);
	}

	// Exp e;
	// Identifier i;
	// ExpList el;
	public void visit(Call n) {
		n.e.accept(this);
		n.i.accept(this);
		for (int i = 0; i < n.el.size(); i++) {
			n.el.elementAt(i).accept(this);
		}
	}

	// int i;
	public void visit(IntegerLiteral n) {
	}

	public void visit(True n) {
	}

	public void visit(False n) {
	}

	// String s;
	public void visit(IdentifierExp n) {
	}

	public void visit(This n) {
	}

	// Exp e;
	public void visit(NewArray n) {
		n.e.accept(this);
	}

	// Identifier i;
	public void visit(NewObject n) {
	}

	// Exp e;
	public void visit(Not n) {
		n.e.accept(this);
	}

	// String s;
	public void visit(Identifier n) {
	}

	@Override
	public void visit(Or n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Diff n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(LessEq lessEq) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(GreaterThan greaterThan) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(GreaterEq greaterEq) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Eq eq) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Mult mult) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Div div) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Mod mod) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Opposite opposite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(BooleanLiteral booleanLiteral) {
		// TODO Auto-generated method stub
		
	}
}
