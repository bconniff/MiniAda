package trees;

public class IncompleteTypeNode extends AbstractTreeNode implements TypeNode {
   public final String name;

   public IncompleteTypeNode(String name) {
      this.name = name;
   }

   public void accept(Visitor v) { v.visit(this); }
}
