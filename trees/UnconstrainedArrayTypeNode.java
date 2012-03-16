package trees;

import java.util.List;

public class UnconstrainedArrayTypeNode extends AbstractTreeNode implements ArrayTypeNode {
   private final List<SubtypeNode> ranges;
   private final TypeNode type;

   public UnconstrainedArrayTypeNode(List<SubtypeNode> ranges, TypeNode type) {
      this.ranges = ranges;
      this.type = type;
   }
}
