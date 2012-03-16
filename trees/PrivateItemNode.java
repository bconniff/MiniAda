package trees;

public class PrivateItemNode extends AbstractTreeNode {
   private final String name;
   private final TypeNode type;

   public PrivateItemNode(String name, TypeNode type) {
      this.name = name;
      this.type = type;
   }
}
