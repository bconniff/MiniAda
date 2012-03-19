package trees;

import visitors.Visitor;
public class AttrSuffixNode extends AbstractTreeNode implements SuffixNode {
   public final String suff;

   public AttrSuffixNode(String suff) {
      this.suff = suff;
   }

   public void accept(Visitor v) { v.visit(this); }
}
