package trees;

import visitors.Visitor;
import java.util.List;

public class AccessTypeNode extends AbstractTreeNode implements TypeNode {
   public final IdNode name;
   public final RangeConstraintNode con;
   public final List<RangeNode> ranges;

   public AccessTypeNode(IdNode n, RangeConstraintNode c) {
      name = n;
      con = c;
      ranges = null;
   }

   public AccessTypeNode(IdNode n, List<RangeNode> r) {
      name = n;
      con = null;
      ranges = r;
   }

   public AccessTypeNode(IdNode n) {
      name = n;
      con = null;
      ranges = null;
   }

   public void accept(Visitor v) { v.visit(this); }
}
