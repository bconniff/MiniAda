package trees;

import java.util.List;
import java.util.ArrayList;

public class ParenSuffixNode extends AbstractTreeNode implements SuffixNode {
   private final List<ExprNode> exprs = new ArrayList<ExprNode>();

   public ParenSuffixNode() {}

   public void add(ExprNode expr) {
      exprs.add(expr);
   }
}
