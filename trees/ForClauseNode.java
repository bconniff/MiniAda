package trees;

import visitors.Visitor;

public class ForClauseNode extends AbstractTreeNode implements LoopClauseNode {
   public final IdNode id;
   public final RangeNode r;
   public final boolean reverse;

   public ForClauseNode(IdNode id, RangeNode r, boolean reverse) {
      this.id = id;
      this.r = r;
      this.reverse = reverse;
   }

}
