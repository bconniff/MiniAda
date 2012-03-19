package trees;

public class IndexConstraintNode extends AbstractTreeNode {
   public IndexConstraintNode() {}

   public void accept(Visitor v) { v.visit(this); }
}
