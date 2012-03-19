package trees;

import visitors.Visitor;
import java.util.List;

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
