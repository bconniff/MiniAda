package trees;

import visitors.Visitor;
import java.util.List;

public class NameNode extends AbstractTreeNode implements ExprNode {
   public final IdNode name;
   public final List<SuffixNode> suffs;

   public NameNode(IdNode n, List<SuffixNode> s) {
      name = n;
      suffs = s;
   }

   public NameNode(IdNode n) {
      this(n, null);
   }

   public void accept(Visitor v) { v.visit(this); }
}
