package trees;

import visitors.Visitor;
public class ChoiceNode extends AbstractTreeNode {
   public final ExprNode a;
   public final ExprNode b;

   public ChoiceNode(ExprNode a, ExprNode b) {
      this.a = a;
      this.b = b;
   }

   public ChoiceNode(ExprNode a) {
      this(a, null);
   }

   public void accept(Visitor v) { v.visit(this); }
}
