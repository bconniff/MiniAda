package trees;

import visitors.Visitor;
import java.util.List;

public class ParenSuffixNode extends AbstractTreeNode implements SuffixNode {
   public final List<ArgNode> exprs;

   public ParenSuffixNode(List<ArgNode> exprs) {
      this.exprs = exprs;
   }

   public void accept(Visitor v) { v.visit(this); }
}
