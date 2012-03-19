package trees;

public class VariantChoiceNode extends AbstractTreeNode {
   public final ExprNode expr;
   public final RecordComponentListNode comps;

   public VariantChoiceNode(ExprNode e, RecordComponentListNode c) {
      expr = e;
      comps = c;
   }

   public void accept(Visitor v) { v.visit(this); }
}
