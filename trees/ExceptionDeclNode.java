package trees;

import java.util.List;

public class ExceptionDeclNode extends AbstractTreeNode implements DeclNode {
   private final List<String> names;

   public ExceptionDeclNode(List<String> names) {
      this.names = names;
   }
}
