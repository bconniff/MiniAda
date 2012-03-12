package trees;

import java.util.List;

public class SubtypeNode implements TypeNode {
   public SubtypeNode(String id, RangeConstraintNode range) {}
   public SubtypeNode(String id, List<RangeNode> range) {}
   public SubtypeNode(RangeConstraintNode range) {}
}
