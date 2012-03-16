package trees;

public class ForClauseNode extends AbstractTreeNode implements LoopClauseNode {
   private final String id;
   private final RangeNode r;
   private final boolean reverse;

   public ForClauseNode(String id, RangeNode r, boolean reverse) {
      this.id = id;
      this.r = r;
      this.reverse = reverse;
   }
}
