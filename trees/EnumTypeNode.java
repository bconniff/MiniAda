package trees;

import visitors.Visitor;
import java.util.List;

public class EnumTypeNode extends AbstractTreeNode implements TypeNode {
   public final List<IdNode> names;

   public EnumTypeNode(List<IdNode> names) {
      this.names = names;
   }

}
