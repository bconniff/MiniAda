package trees;

public class CharValNode extends AbstractTreeNode implements ValNode {
   public final String s;

   public CharValNode(String s) {
      this.s = s;
   }

   public void accept(Visitor v) { v.visit(this); }
}
