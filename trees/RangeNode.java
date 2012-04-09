package trees;

import visitors.Visitor;

public class RangeNode extends AbstractTreeNode {
   public final ExprNode a;
   public final ExprNode b;
   public final IdNode id;
   public final NameNode name;
   public final RangeConstraintNode con;

   public RangeNode(ExprNode a, ExprNode b) {
      this.a = a;
      this.b = b;
      name = null;
      id = null;
      con = null;
   }

   public RangeNode(NameNode n) {
      a = null;
      b = null;
      name = n;
      id = null;
      con = null;
   }

   public RangeNode(IdNode s) {
      a = null;
      b = null;
      name = null;
      id = s;
      con = null;
   }

   public RangeNode(IdNode s, RangeConstraintNode r) {
      a = null;
      b = null;
      name = null;
      id = s;
      con = r;
   }

}
