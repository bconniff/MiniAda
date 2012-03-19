package trees;

import java.util.List;

public class ExceptionDeclNode extends AbstractTreeNode implements DeclNode {
   public final List<String> names;

   public ExceptionDeclNode(List<String> names) {
      this.names = names;
   }

   public void accept(Visitor v) { v.visit(this); }
}
