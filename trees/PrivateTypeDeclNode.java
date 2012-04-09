package trees;

import visitors.Visitor;

public class PrivateTypeDeclNode extends AbstractTreeNode implements DeclNode {
   public final IdNode name;

   public PrivateTypeDeclNode(IdNode name) {
      this.name = name;
   }

}
