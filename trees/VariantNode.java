package trees;

import visitors.Visitor;
import java.util.List;

public class VariantNode extends AbstractTreeNode {
   public final String s;
   public final List<VariantChoiceNode> choices;

   public VariantNode(String s, List<VariantChoiceNode> choices) {
      this.s = s;
      this.choices = choices;
   }

   public void accept(Visitor v) { v.visit(this); }
}
