package trees;

import java.util.List;

public class ProcNode extends AbstractTreeNode implements SubSpecNode {
   private final String name;
   private final List<ParamNode> params;

   public ProcNode(String name, List<ParamNode> params) {
      this.name = name;
      this.params = params;
   }
}
