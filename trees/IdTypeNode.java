package trees;

public class IdTypeNode extends AbstractTreeNode implements TypeNode {
   private final String name;

   public IdTypeNode(String name) {
      this.name = name;
   }
}
