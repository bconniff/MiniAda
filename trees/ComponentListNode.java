package trees;

import java.util.List;
import java.util.ArrayList;

public class ComponentListNode extends AbstractTreeNode {
   private final List<ComponentNode> comps = new ArrayList<ComponentNode>();
   private VariantNode var;

   public ComponentListNode() {}

   public void add(ComponentNode comp) {
      comps.add(comp);
   }

   public void setVariant(VariantNode var) {
      this.var = var;
   }
}
