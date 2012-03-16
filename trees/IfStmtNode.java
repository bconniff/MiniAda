package trees;

import java.util.List;
import java.util.ArrayList;

public class IfStmtNode extends AbstractTreeNode implements StmtNode {
   private final List<IfClauseNode> clauses = new ArrayList<IfClauseNode>();

   public IfStmtNode(IfClauseNode i) {
      clauses.add(i);
   }

   public void addElsif(IfClauseNode i) {
      clauses.add(i);
   }

   public void addElse(IfClauseNode i) {
      clauses.add(i);
   }
}
