package trees;

public class IncompleteTypeNode extends AbstractTreeNode implements TypeNode {
   private final String name;

   public IncompleteTypeNode(String name) {
      this.name = name;
   }
}
