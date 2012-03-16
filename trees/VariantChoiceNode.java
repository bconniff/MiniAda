package trees;

public class VariantChoiceNode extends AbstractTreeNode {
   private final ExprNode expr;
   private final ComponentListNode comps;

   public VariantChoiceNode(ExprNode expr, ComponentListNode comps) {
      this.expr = expr;
      this.comps = comps;
   }
}
