package trees;

public class PragmaArgNode extends AbstractTreeNode {
   private final String name;
   private final ExprNode expr;

   public PragmaArgNode(String name, ExprNode expr) {
      this.name = name;
      this.expr = expr;
   }

   public PragmaArgNode(ExprNode expr) {
      this.name = null;
      this.expr = expr;
   }
}
