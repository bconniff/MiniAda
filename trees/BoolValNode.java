package trees;

public class BoolValNode extends AbstractTreeNode implements ValNode {
   public final boolean val;

   public BoolValNode(String s) {
      val = Boolean.parseBoolean(s.toLowerCase());
   }

   public BoolValNode(boolean b) {
      val = b;
   }

   public void accept(Visitor v) { v.visit(this); }
}
