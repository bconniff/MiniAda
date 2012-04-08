package visitors;

import trees.AbstractTreeNode;

public class Visitor {
   public void visit(AbstractTreeNode n) {
      n.accept(this);
   }

   public void error(String msg) {
      System.err.println(msg);
   }
}
