package trees;

import visitors.Visitor;

public class ArgNode extends AbstractTreeNode {
   public final IdNode name;
   public final ExprNode expr;

   public ArgNode(IdNode name, ExprNode expr) {
      this.name = name;
      this.expr = expr;
   }

   public ArgNode(ExprNode expr) {
      this(null, expr);
   }

}
