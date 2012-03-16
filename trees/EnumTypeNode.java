package trees;

import java.util.List;

public class EnumTypeNode extends AbstractTreeNode implements TypeNode {
   private final List<String> names;

   public EnumTypeNode(List<String> names) {
      this.names = names;
   }
}
