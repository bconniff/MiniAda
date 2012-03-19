package trees;

import visitors.Visitor;
public class IdTypeNode extends AbstractTreeNode implements TypeNode {
   public final String name;

   public IdTypeNode(String name) {
      this.name = name;
   }

   public void accept(Visitor v) { v.visit(this); }
}
