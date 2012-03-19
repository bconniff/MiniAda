package trees;

import visitors.Visitor;
public class IntValNode extends AbstractTreeNode implements ValNode {
   public final String s;

   public IntValNode(String s) { 
      this.s = s;
   }

   public void accept(Visitor v) { v.visit(this); }
}
