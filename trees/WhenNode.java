package trees;

import java.util.List;
import java.util.ArrayList;

public class WhenNode extends AbstractTreeNode {
   private final List<ChoiceNode> choices = new ArrayList<ChoiceNode>();
   private final List<StmtNode> stmts = new ArrayList<StmtNode>();

   public WhenNode() {}

   public void addChoice(ChoiceNode choice) {
      choices.add(choice);
   }

   public void addStmt(StmtNode stmt) {
      stmts.add(stmt);
   }
}
