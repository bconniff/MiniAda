package trees;

public class Visitor {
   public void visit(AbstractTreeNode n) {
      n.accept(this);
   }
}
