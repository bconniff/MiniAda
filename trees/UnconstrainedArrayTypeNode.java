package trees;

import visitors.Visitor;
import java.util.List;

public class UnconstrainedArrayTypeNode extends AbstractTreeNode implements ArrayTypeNode {
   public final List<SubtypeNode> ranges;
   public final TypeNode type;

   public UnconstrainedArrayTypeNode(List<SubtypeNode> ranges, TypeNode type) {
      this.ranges = ranges;
      this.type = type;
   }

   public void accept(Visitor v) { v.visit(this); }
}
