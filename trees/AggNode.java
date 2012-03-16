package trees;

import java.util.List;

public class AggNode extends AbstractTreeNode implements ExprNode {
   private final ExprNode expr;
   private final List<ComponentNode> comps;

   public AggNode(ExprNode expr, List<ComponentNode> comps) {
      this.expr = expr;
      this.comps = comps;
   }
}
