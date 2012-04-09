package visitors;

import symbols.*;
import symbols.types.*;
import symbols.attributes.*;
import trees.*;

public class SemanticsVisitor extends Visitor {
	private final SymbolTable syms;

	public SemanticsVisitor(SymbolTable syms) { this.syms = syms; }
	public SemanticsVisitor() { this(new SymbolTable()); }

	public void visitChildren(AbstractTreeNode n) {
		for (AbstractTreeNode child: n.getChildren())
			child.accept(this);
	}


	public void visit(BinNode bn){
		ExprNode right = bn.r;
		ExprNode left = bn.l;
		right.accept(this);
		left.accept(this);

		if (bn.r.getType().equals(bn.l.getType())) // XXX
			bn.setType(bn.r.getType());
		else
			error("Invalid types for binary operator");
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
		name.accept(this);
		value.accept(this);
	}

	public void visit(NameNode nn){
		for(SuffixNode suff : nn.suffs) {
			suff.accept(this);
		}
	}

	public void visit(ForClauseNode fcn){
		RangeNode range = fcn.r;
		range.accept(this);
	}

	public void visit(RangeNode rn){
		ExprNode lower = rn.a;
		ExprNode upper = rn.b;
		RangeConstraintNode con = rn.con;

		if (lower != null && upper != null) {
			lower.accept(this);
			upper.accept(this);
		} else if (con != null) {
			con.accept(this);
		}
	}

	public void visit(RangeConstraintNode rcn){
		RangeNode range = rcn.range;
		range.accept(this);
	}

	public void visit(FuncNode fn){
      fn.accept(new TopDeclVisitor(syms));
	}

	public void visit(ParamNode pn){
		pn.accept(new TopDeclVisitor(syms));
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
	}

   // XXX
	public void visit(AllSuffixNode sn){ }
	public void visit(AttrSuffixNode sn){ }
	public void visit(DotSuffixNode sn){ }
	public void visit(ParenSuffixNode sn){ }
}

// vim: noet
