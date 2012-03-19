package visitors;

import trees.AbstractTreeNode;

public class Visitor {
   public void visit(AbstractTreeNode n) {
      n.accept(this);
   }
}
