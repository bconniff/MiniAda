package trees;

import visitors.Visitor;
import java.util.List;

public class IfClauseNode extends AbstractTreeNode {
   public final ExprNode expr;
   public final List<StmtNode> stmts;

   public IfClauseNode(ExprNode e, List<StmtNode> s) {
      expr = e;
      stmts = s;
   }

   public IfClauseNode(List<StmtNode> s) {
      this(new BoolValNode(true), s);
   }

}
