package trees;

import java.util.List;
import java.util.ArrayList;

public class CaseStmtNode extends AbstractTreeNode implements StmtNode {
   private final ExprNode expr;
   private final List<WhenNode> whens = new ArrayList<WhenNode>();

   public CaseStmtNode(ExprNode expr) {
      this.expr = expr;
   }

   public void addWhen(WhenNode when) {
      whens.add(when);
   }
}
