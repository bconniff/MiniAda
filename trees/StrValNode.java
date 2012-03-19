package trees;

import visitors.Visitor;

public class StrValNode extends AbstractTreeNode implements ValNode {
   public final String s;

   public StrValNode(String s) {
      this.s = s;
   }

   public void accept(Visitor v) { v.visit(this); }
}
