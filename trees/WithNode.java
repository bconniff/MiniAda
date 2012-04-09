package trees;

import visitors.Visitor;
import java.util.List;

public class WithNode extends AbstractTreeNode implements DirecNode {
   public final List<NameNode> libs;

   public WithNode(List<NameNode> l) {
      libs = l;
   }

}
