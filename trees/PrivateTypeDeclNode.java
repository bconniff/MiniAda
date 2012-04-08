package trees;

import visitors.Visitor;

public class PrivateTypeDeclNode extends AbstractTreeNode implements DeclNode {
   public final IdNode name;

   public PrivateTypeDeclNode(IdNode name) {
      this.name = name;
   }

   public void accept(Visitor v) { v.visit(this); }
}
