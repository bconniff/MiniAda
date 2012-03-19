package trees;

public class SubtypeDeclNode extends AbstractTreeNode implements DeclNode {
   public final String name;
   public final SubtypeNode type;

   public SubtypeDeclNode(String name, SubtypeNode type) {
      this.name = name;
      this.type = type;
   }

   public void accept(Visitor v) { v.visit(this); }
}
