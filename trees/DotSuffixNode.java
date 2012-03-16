package trees;

public class DotSuffixNode extends AbstractTreeNode implements SuffixNode {
   private final String suff;

   public DotSuffixNode(String suff) {
      this.suff = suff;
   }
}
