package trees;

import java.util.List;
import java.util.ArrayList;

public class WhenNode extends AbstractTreeNode {
   public final List<ChoiceNode> choices;
   public final List<StmtNode> stmts;

   public WhenNode(List<ChoiceNode> c, List<StmtNode> s) {
      choices = c;
      stmts = s;
   }

   public WhenNode(List<StmtNode> s) {
      this(null, s);
   }

   public void accept(Visitor v) { v.visit(this); }
}
