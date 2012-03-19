package trees;

import java.util.List;

public class EnumTypeNode extends AbstractTreeNode implements TypeNode {
   public final List<String> names;

   public EnumTypeNode(List<String> names) {
      this.names = names;
   }

   public void accept(Visitor v) { v.visit(this); }
}
