package trees;

import visitors.Visitor;
import java.util.List;

public class IfStmtNode extends AbstractTreeNode implements StmtNode {
   public final List<IfClauseNode> clauses;

   public IfStmtNode(List<IfClauseNode> i) {
      clauses = i;
   }

   public void accept(Visitor v) { v.visit(this); }
}
