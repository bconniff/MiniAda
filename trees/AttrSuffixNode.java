package trees;

public class AttrSuffixNode extends AbstractTreeNode implements SuffixNode {
   private final String suff;

   public AttrSuffixNode(String suff) {
      this.suff = suff;
   }
}
