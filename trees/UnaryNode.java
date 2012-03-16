package trees;

public class UnaryNode extends AbstractTreeNode implements ExprNode {
   private final Op unOp;
   private final ExprNode expr;

   public static enum Op {
      PLUS, MINUS, NOT, ABS;
   }

   public UnaryNode(Op u, ExprNode e) {
      unOp = u;
      expr = e;
   }
}
