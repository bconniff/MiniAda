package trees;

import visitors.Visitor;

public class AttrSuffixNode extends AbstractTreeNode implements SuffixNode {
   public final IdNode suff;

   public AttrSuffixNode(IdNode suff) {
      this.suff = suff;
   }

}
