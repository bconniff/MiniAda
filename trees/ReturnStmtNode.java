package trees;

import visitors.Visitor;

public class ReturnStmtNode extends AbstractTreeNode implements StmtNode {
   public final ExprNode expr;

   public ReturnStmtNode() {
      this.expr = null;
   }

   public ReturnStmtNode(ExprNode expr) {
      this.expr = expr;
   }

}
