package trees;

import java.util.List;

public class AccessTypeNode implements TypeNode {
   public AccessTypeNode(String name) {}

   public void setConstraint(RangeConstraintNode range) {}
   public void setConstraint(List<RangeNode> range) {}
}
