package trees;

public class BinNode extends AbstractTreeNode implements ExprNode{
   public final Op binOp;
   public final ExprNode r;
   public final ExprNode l;

   public static enum Op {
      AND, OR, AND_THEN, OR_ELSE, EQ, LT, GT, LE, NE, GE, PLUS, MINUS, AMP, MULT, DIV, MOD, POW;
   }

   public BinNode(Op binOp, ExprNode r, ExprNode l) {
      this.binOp = binOp;
      this.r = r;
      this.l = l;
   }

   public void accept(Visitor v) { v.visit(this); }
}
