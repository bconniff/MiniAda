package trees;

import visitors.Visitor;

public class StrValNode extends AbstractTreeNode implements ValNode {
   public final String val;

   public StrValNode(String s) {
      val = s.substring(1, s.length()-1);
   }

   public void accept(Visitor v) { v.visit(this); }
}
