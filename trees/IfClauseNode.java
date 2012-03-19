package trees;

import java.util.List;
import java.util.ArrayList;

public class IfClauseNode extends AbstractTreeNode {
   public final ExprNode expr;
   public final List<StmtNode> stmts;

   public IfClauseNode(ExprNode e, List<StmtNode> s) {
      expr = e;
      stmts = s;
   }

   public IfClauseNode(List<StmtNode> s) {
      this(new BoolValNode(true), s);
   }

   public void accept(Visitor v) { v.visit(this); }
}
