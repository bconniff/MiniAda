package trees;

import visitors.Visitor;

public class RangeConstraintNode extends AbstractTreeNode {
   public final RangeNode range;

   public RangeConstraintNode() {
      this.range = null;
   }

   public RangeConstraintNode(RangeNode range) {
      this.range = range;
   }

}
