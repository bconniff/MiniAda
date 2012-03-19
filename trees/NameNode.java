package trees;

import visitors.Visitor;
import java.util.List;

public class NameNode extends AbstractTreeNode implements ExprNode {
   public final String name;
   public final List<SuffixNode> suffs;

   public NameNode(String n, List<SuffixNode> s) {
      name = n;
      suffs = s;
   }

   public NameNode(String n) {
      this(n, null);
   }

   public void accept(Visitor v) { v.visit(this); }
}
