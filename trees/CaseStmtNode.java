package trees;

import visitors.Visitor;
import java.util.List;

public class CaseStmtNode extends AbstractTreeNode implements StmtNode {
   public final ExprNode expr;
   public final List<WhenNode> whens;

   public CaseStmtNode(ExprNode e, List<WhenNode> w) {
      expr = e;
      whens = w;
   }

}
