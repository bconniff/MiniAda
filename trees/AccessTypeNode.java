package trees;

import java.util.List;

public class AccessTypeNode extends AbstractTreeNode implements TypeNode {
   private final String name;
   private RangeConstraintNode con;
   private List<RangeNode> ranges;

   public AccessTypeNode(String name) {
      this.name = name;
   }

   public void setConstraint(RangeConstraintNode range) {
      con = range;
   }

   public void setConstraint(List<RangeNode> range) {
      ranges = range;
   }
}
