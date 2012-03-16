package trees;

import java.util.List;

public class VariantNode extends AbstractTreeNode {
   private final String s;
   private final List<VariantChoiceNode> choices;

   public VariantNode(String s, List<VariantChoiceNode> choices) {
      this.s = s;
      this.choices = choices;
   }
}
