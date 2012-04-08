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
			visit(child);
	}


	public void visit(BinNode bn){
		ExprNode right = bn.r;
		ExprNode left = bn.l;
		right.accept(this);
		left.accept(this);
	}

	public void visit(IfClauseNode icn){
		ExprNode condition = icn.expr;
		condition.accept(this);
		for(StmtNode stmt : icn.stmts) {
			stmt.accept(this);
		}

	}

	public void visit(IfStmtNode isn){
		for(IfClauseNode clause : isn.clauses) {
			clause.accept(this);
		}
	}

	public void visit(LoopClauseNode lcn){

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

	public void visit(SuffixNode sn){

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
		for(ParamNode param : fn.params) {
			param.accept(this);
		}
	}

	public void visit(ParamNode pn){
		TypeNode type = pn.type;
		type.accept(this);
	}

	public void visit(StmtNode sn){

	}

	public void visit(TypeNode tn){

	}

	public void visit(WhileClauseNode wcn){
		ExprNode condition = wcn.expr;
		condition.accept(this);
	}

	public void visit(UnaryNode un){
		ExprNode expr = un.expr;
		expr.accept(this);
	}
}
