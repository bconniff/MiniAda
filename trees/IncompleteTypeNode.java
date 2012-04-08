package trees;

import visitors.Visitor;

public class IncompleteTypeNode extends AbstractTreeNode implements TypeNode {
   public final IdNode name;

   public IncompleteTypeNode(IdNode name) {
      this.name = name;
   }

   public void accept(Visitor v) { v.visit(this); }
}
