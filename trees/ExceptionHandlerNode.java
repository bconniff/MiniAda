package trees;

import java.util.List;
import java.util.ArrayList;

public class ExceptionHandlerNode extends AbstractTreeNode {
   private final WhenNode expr;
   private final List<StmtNode> stmts = new ArrayList<StmtNode>();

   public ExceptionHandlerNode(WhenNode expr) {
      this.expr = expr;
   }

   public void addStmt(StmtNode stmt) {
      stmts.add(stmt);
   }
}
