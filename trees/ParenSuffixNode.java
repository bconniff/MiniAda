package trees;

import java.util.List;
import java.util.ArrayList;

public class ParenSuffixNode extends AbstractTreeNode implements SuffixNode {
   public final List<ArgNode> exprs;

   public ParenSuffixNode(List<ArgNode> exprs) {
      this.exprs = exprs;
   }

   public void accept(Visitor v) { v.visit(this); }
}
