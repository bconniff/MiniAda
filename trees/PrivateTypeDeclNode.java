package trees;

public class PrivateTypeDeclNode extends AbstractTreeNode implements DeclNode {
   private final String name;

   public PrivateTypeDeclNode(String name) {
      this.name = name;
   }
}
