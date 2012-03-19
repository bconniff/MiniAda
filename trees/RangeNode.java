package trees;

public class RangeNode extends AbstractTreeNode {
   public final ExprNode a;
   public final ExprNode b;
   public final String name;
   public final RangeConstraintNode con;
   public final AttrNode attr;

   public RangeNode(ExprNode a, ExprNode b) {
      this.a = a;
      this.b = b;
      name = null;
      con = null;
      attr = null;
   }

   public RangeNode(String s) {
      a = null;
      b = null;
      name = s;
      con = null;
      attr = null;
   }

   public RangeNode(String s, RangeConstraintNode r) {
      a = null;
      b = null;
      name = s;
      con = r;
      attr = null;
   }

   public RangeNode(AttrNode attr) {
      a = null;
      b = null;
      name = null;
      con = null;
      this.attr = attr;
   }

   public void accept(Visitor v) { v.visit(this); }
}
