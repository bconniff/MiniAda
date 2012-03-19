package trees;

import java.util.List;
import java.util.ArrayList;

public class WithNode extends AbstractTreeNode implements DirecNode {
   public final List<NameNode> libs;

   public WithNode(List<NameNode> l) {
      libs = l;
   }

   public void accept(Visitor v) { v.visit(this); }
}
