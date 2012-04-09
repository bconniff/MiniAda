package trees;

import visitors.Visitor;

public class ExitStmtNode extends AbstractTreeNode implements StmtNode {
   public final IdNode id;
   public final ExprNode expr;

   public ExitStmtNode(IdNode i, ExprNode e) {
      id = i;
      expr = e;
   }

   public ExitStmtNode(IdNode i) {
      this(i, new BoolValNode(true));
   }

   public ExitStmtNode(ExprNode e) {
      this(null, e);
   }

   public ExitStmtNode() {
      this(null, new BoolValNode(true));
   }

}
