package trees;

public class PrivateItemNode extends AbstractTreeNode {
   public final String name;
   public final TypeNode type;

   public PrivateItemNode(String name, TypeNode type) {
      this.name = name;
      this.type = type;
   }

   public void accept(Visitor v) { v.visit(this); }
}
