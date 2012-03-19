package trees;

import visitors.Visitor;
public class WhileClauseNode extends AbstractTreeNode implements LoopClauseNode {
   public final ExprNode expr;

   public WhileClauseNode(ExprNode expr) {
      this.expr = expr;
   }

   public void accept(Visitor v) { v.visit(this); }
}
