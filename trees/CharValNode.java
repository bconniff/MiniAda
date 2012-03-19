package trees;

import visitors.Visitor;

public class CharValNode extends AbstractTreeNode implements ValNode {
   public final char val;

   public CharValNode(String s) {
      val = s.charAt(1);
   }

   public void accept(Visitor v) { v.visit(this); }
}
