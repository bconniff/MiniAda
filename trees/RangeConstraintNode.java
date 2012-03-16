package trees;

public class RangeConstraintNode extends AbstractTreeNode {
   private final RangeNode range;

   public RangeConstraintNode() {
      this.range = null;
   }

   public RangeConstraintNode(RangeNode range) {
      this.range = range;
   }
}
