package trees;

public class BinNode implements ExprNode{
   public static enum Op {
      AND, OR, AND_THEN, OR_ELSE, EQ, LT, GT, LE, NE, GE, PLUS, MINUS, AMP, MULT, DIV, MOD, POW;
   }

   public BinNode(Op b, ExprNode r, ExprNode l) {
      // code
   }
}
