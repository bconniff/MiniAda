package visitors;

import symbols.*;
import symbols.types.*;
import symbols.attributes.*;
import emittor.*;
import trees.*;

public class CodeGenVisitor extends Visitor {
   protected final String name;
	protected final Emittor em;
	protected int label = -1;
	protected int iflabel = 0;

   public CodeGenVisitor(String name) {
      this.name = name.toLowerCase();
		em = new Emittor(name);
   }

	public void visitChildren(AbstractTreeNode n) {
		for (AbstractTreeNode child: n.getChildren())
			child.accept(this);
	}

	public void visit(MiniAdaTree n) {
      em.emit(".class public "+name+"\n");
      em.emit(".super java/lang/Object\n\n");
      visitChildren(n);
      em.emit(".method public static main([Ljava/lang/String;)V\n");
      em.emit("\t.limit locals 1\n");
      em.emit("\t.limit stack 0\n");
      em.emit("\tinvokestatic "+name+"/"+name+"()V\n");
		em.emit("\treturn\n");
      em.emit(".end method\n");
   }

	public void visit(LoopStmtNode lsn) {
		int mylabel = ++label;

		lsn.loop.accept(this);

		for (StmtNode s: lsn.stmts)
			s.accept(this);

		em.emit("\tgoto l"+mylabel+"\n");
		em.emit("\tend_l"+mylabel+":\n");
	}

	public void visit(WhileClauseNode wcn) {
		em.emit("\tl"+label+":\n");
		wcn.expr.accept(this);
		em.emit("\tifeq end_l"+label+"\n");
	}

	public void visit(ForClauseNode fcn) {
		final boolean reverse = fcn.reverse;

		(reverse ? fcn.r.b : fcn.r.a).accept(this);
		em.emit("\ticonst_1\n");
		em.emit("\ti"+(reverse ? "add" : "sub")+"\n");
		em.emitStore(fcn.id.getType(), fcn.id.num);
		em.emit("\tl"+label+":\n");
		em.emitLoad(fcn.id.getType(), fcn.id.num);
		(reverse ? fcn.r.a : fcn.r.b).accept(this);
		em.emit("\tisub\n");
		em.emit("\tif"+(reverse ? "le" : "ge")+" end_l"+label+"\n");
		em.emitLoad(fcn.id.getType(), fcn.id.num);
		em.emit("\ticonst_1\n");
		em.emit("\ti"+(reverse ? "sub" : "add")+"\n");
		em.emitStore(fcn.id.getType(), fcn.id.num);
	}

	public void visit(IfClauseNode icn, int last) {
		icn.expr.accept(this);
		em.emit("\tifeq i"+iflabel+"\n");
		
		for (StmtNode s: icn.stmts)
			s.accept(this);

		if (last != iflabel)
			em.emit("\tgoto i"+last+"\n");
		em.emit("\ti"+iflabel+":\n");

		iflabel++;
	}

	public void visit(IfStmtNode isn) {
		final int lastlabel = iflabel + isn.clauses.size() - 1;
		for (IfClauseNode i: isn.clauses)
			visit(i, lastlabel);
	}

	public void visit(BinNode bn) {
		ExprNode right = bn.r;
		ExprNode left = bn.l;
		right.accept(this);
		//em.emitStore(right.getType(), localNum++);
		left.accept(this);
		//em.emitLoad(right.getType(), --localNum);

		switch (bn.binOp) {
			case MOD:
			case REM:
				em.emitRem();
				break;
			case EQ:
				em.emitEQ(right.getType());
				break;
			case NE:
				em.emitNE(right.getType());
				break;
			case GT:
				em.emitGT(right.getType());
				break;
			case GE:
				em.emitGE(right.getType());
				break;
			case LT:
				em.emitLT(right.getType());
				break;
			case LE:
				em.emitLE(right.getType());
				break;
			case PLUS:
				em.emitAdd(right.getType());
				break;
			case MINUS:
				em.emitSub(right.getType());
				break;
			case MULT:
				em.emitMul(right.getType());
				break;
			case DIV:
				em.emitDiv(right.getType());
				break;
			default:
				error("Oops, don't know what to do with your binary ops");
				break;
		}
	}

	public void visit(UnaryNode un) {
		ExprNode expr = un.expr;
		expr.accept(this);

		switch (un.unOp) {
			case MINUS:
				em.emitNeg(expr.getType());
				break;
			case ABS:
				em.emitAbs(expr.getType());
				break;
			case PLUS:
				break;
			default:
				error("Oops, don't know what to do with your binary ops");
				break;
		}
	}

	public void visit(CallStmtNode n) {
		n.name.accept(this);
	}

	public void visit(NameNode n) {
		if (n.suffs == null || n.suffs.size() == 0) {
			em.emitLoad(n.getType(), n.name.num);
		} else {
			if (n.name.id.toLowerCase().equals("put_line")
					&& n.suffs.size() == 1
					&& n.suffs.get(0) instanceof ParenSuffixNode)
			{
				final ParenSuffixNode p = (ParenSuffixNode)n.suffs.get(0);

				if (p.exprs != null && p.exprs.size() == 1) {
					final ExprNode e = p.exprs.get(0).expr;

					em.emit("\tgetstatic java/lang/System/out Ljava/io/PrintStream;\n");
					e.accept(this);
					em.emit("\tinvokevirtual java/io/PrintStream/println(");
					em.emit(e.getType());
					em.emit(")V\n");
				} else {
					System.err.println("I don't know what to do");
				}
			} else {
				System.err.println("I don't know what to do");
			}
		}
	}

	public void visit(FloatValNode f) {
		em.emit("\tldc "+f.val+"\n");
	}

	public void visit(IntValNode i) {
		em.emit("\tldc "+i.val+"\n");
	}

	public void visit(BoolValNode b) {
		em.emit("\tldc "+(b.val ? 1 : 0)+"\n");
	}

	public void visit(StrValNode s) {
		em.emit("\tldc \""+s.val+"\"\n");
	}

	public void visit(ObjDeclNode o) {
		if (o.init != null) {
			o.init.accept(this);

			for (IdNode i: o.names) {
				em.emit("\tdup\n");
				em.emitStore(o.type.getType(), i.num);
			}

			em.emit("\tpop\n");
		}
	}

   public void visit(SubDeclNode n) {
      n.spec.accept(this);

		em.emit("\t.limit stack 100\n");
		em.emit("\t.limit locals 100\n");

      visitChildren(n.body);

		em.emit("\treturn\n");
		em.emit(".end method\n\n");
   }

	public void visit(AssignStmtNode asn){
		asn.expr.accept(this);
		/*em.emit("\tdup\n");
		em.emit("\tgetstatic java/lang/System/out Ljava/io/PrintStream;\n");
		em.emit("\tswap\n");
		em.emit("\tinvokevirtual java/io/PrintStream/println(");
		em.emit(asn.name.getType());
		em.emit(")V\n"); */
		em.emitStore(asn.name.getType(), asn.name.name.num);
	}

	public void visit(ProcNode n) {
		em.emit(".method public static "+n.name.id.toLowerCase()+"(");

		for (ParamNode p: n.params) {
			em.emit(p.getType());
		}

		em.emit(")V\n");
	}

	public void visit(FuncNode n) {
		em.emit(".method public static "+n.name.id.toLowerCase()+"(");

		for (ParamNode p: n.params) {
			em.emit(p.getType());
		}

		em.emit(")");
		em.emit(n.ret.getType());
		em.emit("\n");
	}

	public void visit(NullStmtNode n) {
		
	}
}

// vim: noet
