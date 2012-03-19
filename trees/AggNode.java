package trees;

import visitors.Visitor;
import java.util.List;

public class AggNode extends AbstractTreeNode implements ExprNode {
   public final ExprNode expr;
   public final List<ComponentNode> comps;

   public AggNode(ExprNode expr, List<ComponentNode> comps) {
      this.expr = expr;
      this.comps = comps;
   }

   public void accept(Visitor v) { v.visit(this); }
}
