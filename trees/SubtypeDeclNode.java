package trees;

import visitors.Visitor;

public class SubtypeDeclNode extends AbstractTreeNode implements DeclNode {
   public final IdNode name;
   public final SubtypeNode type;

   public SubtypeDeclNode(IdNode name, SubtypeNode type) {
      this.name = name;
      this.type = type;
   }

}
