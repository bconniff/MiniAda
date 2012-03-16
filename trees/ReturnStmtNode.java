package trees;

public class ReturnStmtNode extends AbstractTreeNode implements StmtNode {
   private final ExprNode expr;

   public ReturnStmtNode() {
      this.expr = null;
   }

   public ReturnStmtNode(ExprNode expr) {
      this.expr = expr;
   }
}
