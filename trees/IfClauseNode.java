package trees;

import java.util.List;
import java.util.ArrayList;

public class IfClauseNode extends AbstractTreeNode {
   private ExprNode expr;
   private List<StmtNode> stmts = new ArrayList<StmtNode>();

   public IfClauseNode() {}

   public void setExpr(ExprNode expr) {
      this.expr = expr;
   }

   public void addStmt(StmtNode stmt) {
      stmts.add(stmt);
   }
}
