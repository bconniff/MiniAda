package trees;

public class BinNode extends AbstractTreeNode implements ExprNode{
   private final Op binOp;
   private final ExprNode r;
   private final ExprNode l;

   public static enum Op {
      AND, OR, AND_THEN, OR_ELSE, EQ, LT, GT, LE, NE, GE, PLUS, MINUS, AMP, MULT, DIV, MOD, POW;
   }

   public BinNode(Op binOp, ExprNode r, ExprNode l) {
      this.binOp = binOp;
      this.r = r;
      this.l = l;
   }
}
