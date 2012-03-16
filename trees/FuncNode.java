package trees;

import java.util.List;

public class FuncNode extends AbstractTreeNode implements SubSpecNode {
   private final String name;
   private final List<ParamNode> params;
   private final String ret;

   public FuncNode(String name, List<ParamNode> params, String ret) {
      this.name = name;
      this.params = params;
      this.ret = ret;
   }
}
