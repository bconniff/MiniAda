package trees;

import visitors.Visitor;

public class UnaryNode extends AbstractTreeNode implements ExprNode {
   public final Op unOp;
   public final ExprNode expr;

   public static enum Op {
      PLUS, MINUS, NOT, ABS;
   }

   public UnaryNode(Op u, ExprNode e) {
      unOp = u;
      expr = e;
   }

   public void accept(Visitor v) { v.visit(this); }
}
