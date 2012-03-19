package trees;

import java.util.List;
import java.util.ArrayList;

public class UseNode extends AbstractTreeNode implements DirecNode,DeclNode {
   public final List<NameNode> libs;

   public UseNode(List<NameNode> l) {
      libs = l;
   }

   public void accept(Visitor v) { v.visit(this); }
}
