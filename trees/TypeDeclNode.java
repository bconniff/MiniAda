package trees;

import visitors.Visitor;

public class TypeDeclNode extends AbstractTreeNode implements DeclNode {
   public final IdNode name;
   public final TypeNode type;

   public TypeDeclNode(IdNode name, TypeNode type) {
      this.name = name;
      this.type = type;
   }

   public void accept(Visitor v) { v.visit(this); }
}
