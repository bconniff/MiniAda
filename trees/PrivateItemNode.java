package trees;

import visitors.Visitor;

public class PrivateItemNode extends AbstractTreeNode {
   public final IdNode name;
   public final TypeNode type;

   public PrivateItemNode(IdNode name, TypeNode type) {
      this.name = name;
      this.type = type;
   }

   public void accept(Visitor v) { v.visit(this); }
}
