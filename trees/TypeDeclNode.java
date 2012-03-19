package trees;

public class TypeDeclNode extends AbstractTreeNode implements DeclNode {
   public final String name;
   public final TypeNode type;

   public TypeDeclNode(String name, TypeNode type) {
      this.name = name;
      this.type = type;
   }

   public void accept(Visitor v) { v.visit(this); }
}
