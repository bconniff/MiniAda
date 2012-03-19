package trees;

import java.util.List;
import java.util.ArrayList;

public class CaseStmtNode extends AbstractTreeNode implements StmtNode {
   public final ExprNode expr;
   public final List<WhenNode> whens;

   public CaseStmtNode(ExprNode e, List<WhenNode> w) {
      expr = e;
      whens = w;
   }

   public void accept(Visitor v) { v.visit(this); }
}
