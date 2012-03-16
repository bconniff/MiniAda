package trees;

public class ExitStmtNode extends AbstractTreeNode implements StmtNode {
   private String id;
   private ExprNode expr;

   public ExitStmtNode() {}

   public void setName(String id) {
      this.id = id;
   }

   public void setWhen(ExprNode expr) {
      this.expr = expr;
   }
}
