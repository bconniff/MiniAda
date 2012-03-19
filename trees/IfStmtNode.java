package trees;

import java.util.List;
import java.util.ArrayList;

public class IfStmtNode extends AbstractTreeNode implements StmtNode {
   public final List<IfClauseNode> clauses;

   public IfStmtNode(List<IfClauseNode> i) {
      clauses = i;
   }

   public void accept(Visitor v) { v.visit(this); }
}
