package trees;

import visitors.Visitor;
public class ForClauseNode extends AbstractTreeNode implements LoopClauseNode {
   public final String id;
   public final RangeNode r;
   public final boolean reverse;

   public ForClauseNode(String id, RangeNode r, boolean reverse) {
      this.id = id;
      this.r = r;
      this.reverse = reverse;
   }

   public void accept(Visitor v) { v.visit(this); }
}
