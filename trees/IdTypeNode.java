package trees;

import visitors.Visitor;

public class IdTypeNode extends AbstractTreeNode implements TypeNode {
   public final IdNode name;

   public IdTypeNode(IdNode name) {
      this.name = name;
   }

}
