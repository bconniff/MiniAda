package trees;

import java.util.List;
import java.util.ArrayList;

public class LoopStmtNode extends AbstractTreeNode implements StmtNode {
   private String name;
   private LoopClauseNode loop;
   private List<StmtNode> stmts = new ArrayList<StmtNode>();

   public LoopStmtNode() {}

   public void setName(String name) {
      this.name = name;
   }

   public void setClause(LoopClauseNode loop) {
      this.loop = loop;
   }

   public void addStmt(StmtNode stmt) {
      stmts.add(stmt);
   }
}
