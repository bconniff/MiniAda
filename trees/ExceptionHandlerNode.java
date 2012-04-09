package trees;

import visitors.Visitor;
import java.util.List;

public class ExceptionHandlerNode extends AbstractTreeNode {
   public final WhenNode expr;
   public final List<StmtNode> stmts;

   public ExceptionHandlerNode(WhenNode e, List<StmtNode> s) {
      expr = e;
      stmts = s;
   }

}
