package trees;

import visitors.Visitor;
import java.util.List;

public class UseNode extends AbstractTreeNode implements DirecNode,DeclNode {
   public final List<NameNode> libs;

   public UseNode(List<NameNode> l) {
      libs = l;
   }

   public void accept(Visitor v) { v.visit(this); }
}
