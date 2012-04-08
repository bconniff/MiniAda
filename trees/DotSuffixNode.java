package trees;

import visitors.Visitor;

public class DotSuffixNode extends AbstractTreeNode implements SuffixNode {
   public final IdNode suff;

   public DotSuffixNode(IdNode suff) {
      this.suff = suff;
   }

   public void accept(Visitor v) { v.visit(this); }
}
