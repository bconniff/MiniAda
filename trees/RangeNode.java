package trees;

public class RangeNode extends AbstractTreeNode {
   private final ExprNode a;
   private final ExprNode b;
   private final String name;
   private final RangeConstraintNode con;
   private final AttrNode attr;

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
}
