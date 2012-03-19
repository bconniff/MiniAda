package trees;

import java.util.List;

public class AccessTypeNode extends AbstractTreeNode implements TypeNode {
   public final String name;
   public final RangeConstraintNode con;
   public final List<RangeNode> ranges;

   public AccessTypeNode(String n, RangeConstraintNode c) {
      name = n;
      con = c;
      ranges = null;
   }

   public AccessTypeNode(String n, List<RangeNode> r) {
      name = n;
      con = null;
      ranges = r;
   }

   public AccessTypeNode(String n) {
      name = n;
      con = null;
      ranges = null;
   }

   public void accept(Visitor v) { v.visit(this); }
}
