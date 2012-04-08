package trees;

import visitors.Visitor;
import java.util.List;

public class SubtypeNode extends AbstractTreeNode implements TypeNode {
   public final IdNode id;
   public final RangeConstraintNode con;
   public final List<RangeNode> ranges;

   public SubtypeNode(IdNode id, RangeConstraintNode range) {
      this.id = id;
      con = range;
      ranges = null;
   }

   public SubtypeNode(IdNode id, List<RangeNode> range) {
      this.id = id;
      ranges = range;
      con = null;
   }

   public SubtypeNode(RangeConstraintNode range) {
      con = range;
      id = null;
      ranges = null;
   }

   public void accept(Visitor v) { v.visit(this); }
}
