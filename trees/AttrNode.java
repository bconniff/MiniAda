package trees;

public class AttrNode extends AbstractTreeNode {
   public final String a;
   public final String b;

   public AttrNode(String a, String b) {
      this.a = a;
      this.b = b;
   }

   public void accept(Visitor v) { v.visit(this); }
}
