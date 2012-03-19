package trees;

public class PrivateTypeDeclNode extends AbstractTreeNode implements DeclNode {
   public final String name;

   public PrivateTypeDeclNode(String name) {
      this.name = name;
   }

   public void accept(Visitor v) { v.visit(this); }
}
