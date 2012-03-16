package trees;

public class SubtypeDeclNode extends AbstractTreeNode implements DeclNode {
   private final String name;
   private final SubtypeNode type;

   public SubtypeDeclNode(String name, SubtypeNode type) {
      this.name = name;
      this.type = type;
   }
}
