package trees;

public class DotSuffixNode extends AbstractTreeNode implements SuffixNode {
   public final String suff;

   public DotSuffixNode(String suff) {
      this.suff = suff;
   }

   public void accept(Visitor v) { v.visit(this); }
}
