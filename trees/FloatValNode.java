package trees;

public class FloatValNode extends AbstractTreeNode implements ValNode {
   public final String s;

   public FloatValNode(String s) { 
      this.s = s;
   }

   public void accept(Visitor v) { v.visit(this); }
}
