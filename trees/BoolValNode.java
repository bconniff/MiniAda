package trees;

import visitors.Visitor;

public class BoolValNode extends AbstractTreeNode implements ValNode {
   public final boolean val;

   public BoolValNode(String s) {
      val = Boolean.parseBoolean(s.toLowerCase());
   }

   public BoolValNode(boolean b) {
      val = b;
   }

}
