package trees;

public class ChoiceNode extends AbstractTreeNode {
   private ExprNode a;
   private ExprNode b;

   public ChoiceNode() { }

   public ChoiceNode(ExprNode a) {
      this.a = a;
   }

   public ChoiceNode(ExprNode a, ExprNode b) {
      this.a = a;
      this.b = b;
   }

   public void setVal(ExprNode a) {
      this.a = a;
      this.b = null;
   }

   public void setRange(ExprNode a, ExprNode b) {
      this.a = a;
      this.b = b;
   }
}
