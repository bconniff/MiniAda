package trees;

import visitors.Visitor;
import java.util.List;

public class VariantChoiceNode extends AbstractTreeNode {
   public final ExprNode expr;
   public final List<RecordItemNode> comps;

   public VariantChoiceNode(ExprNode e, List<RecordItemNode> c) {
      expr = e;
      comps = c;
   }

}
