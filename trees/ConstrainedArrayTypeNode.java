package trees;

import java.util.List;

public class ConstrainedArrayTypeNode extends AbstractTreeNode implements ArrayTypeNode {
   public final List<RangeNode> ranges;
   public final TypeNode type;

   public ConstrainedArrayTypeNode(List<RangeNode> ranges, TypeNode type) {
      this.ranges = ranges;
      this.type = type;
   }

   public void accept(Visitor v) { v.visit(this); }
}
