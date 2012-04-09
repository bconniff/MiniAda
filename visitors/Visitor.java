package visitors;

import trees.AbstractTreeNode;

public class Visitor {
   public void visit(AbstractTreeNode n) {
      //n.accept(this);
      visit(n.getClass().cast(n));
   }

   public void error(String msg) {
      System.err.println(msg);
   }
}
