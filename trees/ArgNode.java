package trees;

import visitors.Visitor;
public class ArgNode extends AbstractTreeNode {
   public final String name;
   public final ExprNode expr;

   public ArgNode(String name, ExprNode expr) {
      this.name = name;
      this.expr = expr;
   }

   public ArgNode(ExprNode expr) {
      this(null, expr);
   }

   public void accept(Visitor v) { v.visit(this); }
}
