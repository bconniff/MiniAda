package trees;

public class WhileClauseNode extends AbstractTreeNode implements LoopClauseNode {
   private final ExprNode expr;

   public WhileClauseNode(ExprNode expr) {
      this.expr = expr;
   }
}
