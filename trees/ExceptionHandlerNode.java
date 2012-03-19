package trees;

import java.util.List;
import java.util.ArrayList;

public class ExceptionHandlerNode extends AbstractTreeNode {
   public final WhenNode expr;
   public final List<StmtNode> stmts;

   public ExceptionHandlerNode(WhenNode e, List<StmtNode> s) {
      expr = e;
      stmts = s;
   }

   public void accept(Visitor v) { v.visit(this); }
}
