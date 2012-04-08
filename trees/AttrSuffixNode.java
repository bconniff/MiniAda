package trees;

import visitors.Visitor;

public class AttrSuffixNode extends AbstractTreeNode implements SuffixNode {
   public final IdNode suff;

   public AttrSuffixNode(IdNode suff) {
      this.suff = suff;
   }

   public void accept(Visitor v) { v.visit(this); }
}
