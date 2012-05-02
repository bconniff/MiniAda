package visitors;

import symbols.*;
import symbols.types.*;
import symbols.attributes.*;
import trees.*;

public class SemanticsVisitor extends Visitor {
	protected final SymbolTable syms;

	public SemanticsVisitor(SymbolTable syms) { this.syms = syms; }

	public void visitChildren(AbstractTreeNode n) {
		for (AbstractTreeNode child: n.getChildren())
			child.accept(this);
	}

	public void visit(MiniAdaTree n) { visitChildren(n); }

	public void visit(CallStmtNode n) {
		n.name.accept(this);
		//error("Subprogram calls aren't supported (yet)");
	}

	public void visit(WithNode n) {
		error("With is not supported (yet)");
	}

	public void visit(UseNode n) {
		error("Use is not supported (yet)");
	}

	public void visit(IdNode n) {
		SymbolAttributes a = syms.get(n.id);

		if (a == null) {
			error("Name "+n.id+" is not defined");
		} else if (a instanceof VariableAttributes) {
			n.setType(((VariableAttributes)a).variableType);
			n.setAttr(a);
			n.num = syms.getNum(n.id);
		} else {
			error("Name "+n.id+" is not a variable");
		}
	}

	public void visit(BinNode bn){
		ExprNode right = bn.r;
		ExprNode left = bn.l;
		right.accept(this);
		left.accept(this);

		TypeDescriptor td = new ErrorTypeDescriptor();

		switch (bn.binOp) {
			case AND:
			case AND_THEN:
			case OR:
			case OR_ELSE:
			case XOR:
				td = Operators.boolOp(left.getType(), right.getType());
				break;

			case EQ:
			case NE:
				td = Operators.equalOp(left.getType(), right.getType());
				break;

			case LT:
			case LE:
			case GT:
			case GE:
				td = Operators.compOp(left.getType(), right.getType());
				break;

			case PLUS:
			case MINUS:
			case MULT:
			case DIV:
			case POW:
				td = Operators.arithOp(left.getType(), right.getType());
				break;

			case MOD:
			case REM:
				td = Operators.modOp(left.getType(), right.getType());
				break;

			case AMP:
				td = Operators.concatOp(left.getType(), right.getType());
				break;
		}

		if (!td.isError())
			bn.setType(td);
		else
			error("Invalid types "+right.getType()+" and "+left.getType()+" for binary operator "+bn.binOp);
	}

	public void visit(IfClauseNode icn){
		ExprNode condition = icn.expr;
		condition.accept(this);
		for(StmtNode stmt : icn.stmts) {
			stmt.accept(this);
		}

		if (!(condition.getType() instanceof BooleanTypeDescriptor))
			error("Expected boolean condition in if clause");
	}

	public void visit(IfStmtNode isn){
		for(IfClauseNode clause : isn.clauses) {
			clause.accept(this);
		}
	}

	public void visit(LoopStmtNode lsn){
		LoopClauseNode loop = lsn.loop;
		loop.accept(this);
		for(StmtNode stmt : lsn.stmts) {
			stmt.accept(this);
		}
	}

	public void visit(AssignStmtNode asn){
		NameNode name = asn.name;
		ExprNode value = asn.expr;
		name.accept(new LHSSemanticsVisitor(syms));
		value.accept(this);

		if (!name.getType().isAssignable(value.getType())) {
			error("Can't assign "+value.getType()+" to "+name.getType());
		}
	}

	public void visit(ParenSuffixNode p) {
		for (ArgNode a: p.exprs)
			a.expr.accept(this);
	}

	public void visit(NameNode nn){
		nn.name.accept(this);

		TypeDescriptor t = nn.name.getType();

		if (nn.suffs != null) {
			for (int i = 0; i < nn.suffs.size(); i++) {
				nn.suffs.get(i).accept(this);
				t = t.applySuffix(nn.suffs.get(i));
			}

			if (t instanceof ErrorTypeDescriptor) {
				error("Invalid suffix for name "+nn.name.id);
			}
		}

		nn.setType(t);
		nn.setAttr(new TypeAttributes(t));
	}

	public void visit(ForClauseNode fcn){
		RangeNode range = fcn.r;

		TypeDescriptor td = new IntegerTypeDescriptor();
		SymbolAttributes sa = new VariableAttributes(td);

		fcn.id.setType(td);
		fcn.id.setAttr(new VariableAttributes(td));

		syms.add(fcn.id.id, sa);

		fcn.id.num = syms.getNum(fcn.id.id);

		range.accept(this);
	}

	public void visit(RangeNode rn){
		ExprNode lower = rn.a;
		ExprNode upper = rn.b;
		RangeConstraintNode con = rn.con;

		if (lower != null && upper != null) {
			lower.accept(this);
			upper.accept(this);
			if (!(lower.getType() instanceof IntegerTypeDescriptor
					&& upper.getType() instanceof IntegerTypeDescriptor))
				error("Only integer ranges are supported so far");
		} else {
			error("Only integer ranges are supported so far");
		}
	}

	public void visit(RangeConstraintNode rcn){
		RangeNode range = rcn.range;
		range.accept(this);
	}

	public void visit(SubDeclNode s){
      s.accept(new TopDeclVisitor(syms));
	}

	public void visit(WhileClauseNode wcn){
		ExprNode condition = wcn.expr;
		condition.accept(this);

		if (!(condition.getType() instanceof BooleanTypeDescriptor)) {
			error("Expected boolean condition in while clause");
		}
	}

	public void visit(UnaryNode un){
		ExprNode expr = un.expr;
		expr.accept(this);

		TypeDescriptor td = new ErrorTypeDescriptor();

		switch (un.unOp) {
			case PLUS:
			case MINUS:
			case ABS:
				td = Operators.unArithOp(expr.getType());
				break;

			case NOT:
				td = Operators.unBoolOp(expr.getType());
				break;
		}

		if (!td.isError())
			un.setType(td);
		else
			error("Invalid type "+expr.getType()+" for unary operator "+un.unOp);
	}

	public void visit(StrValNode i) {
		final TypeDescriptor td = new StringTypeDescriptor();
		i.setType(td);
		i.setAttr(new TypeAttributes(td));
	}

	public void visit(CharValNode i) {
		final TypeDescriptor td = new CharacterTypeDescriptor();
		i.setType(td);
		i.setAttr(new TypeAttributes(td));
	}

	public void visit(BoolValNode i) {
		final TypeDescriptor td = new BooleanTypeDescriptor();
		i.setType(td);
		i.setAttr(new TypeAttributes(td));
	}

	public void visit(IntValNode i) {
		final TypeDescriptor td = new IntegerTypeDescriptor();
		i.setType(td);
		i.setAttr(new TypeAttributes(td));
	}

	public void visit(FloatValNode i) {
		final TypeDescriptor td = new FloatTypeDescriptor();
		i.setType(td);
		i.setAttr(new TypeAttributes(td));
	}

	public void visit(ObjDeclNode o) {
		o.accept(new TopDeclVisitor(syms));
	}

	public void visit(NullStmtNode n) { }

   // XXX
	public void visit(AllSuffixNode sn){ }
	public void visit(AttrSuffixNode sn){ }
	public void visit(DotSuffixNode sn){ }
}

// vim: noet
