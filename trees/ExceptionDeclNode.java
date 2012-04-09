package trees;

import visitors.Visitor;
import java.util.List;

public class ExceptionDeclNode extends AbstractTreeNode implements DeclNode {
   public final List<IdNode> names;

   public ExceptionDeclNode(List<IdNode> names) {
      this.names = names;
   }

}
