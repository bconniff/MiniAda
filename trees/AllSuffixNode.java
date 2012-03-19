package trees;

public class AllSuffixNode extends AbstractTreeNode implements SuffixNode {
   public AllSuffixNode() {}

   public void accept(Visitor v) { v.visit(this); }
}
