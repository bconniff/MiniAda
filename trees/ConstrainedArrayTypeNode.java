package trees;

import java.util.List;

public class ConstrainedArrayTypeNode extends AbstractTreeNode implements ArrayTypeNode {
   private final List<RangeNode> ranges;
   private final TypeNode type;

   public ConstrainedArrayTypeNode(List<RangeNode> ranges, TypeNode type) {
      this.ranges = ranges;
      this.type = type;
   }
}
