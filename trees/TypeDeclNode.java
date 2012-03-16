package trees;

public class TypeDeclNode extends AbstractTreeNode implements DeclNode {
   private final String name;
   private final TypeNode type;

   public TypeDeclNode(String name, TypeNode type) {
      this.name = name;
      this.type = type;
   }
}
