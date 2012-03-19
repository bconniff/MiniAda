package trees;

import java.util.List;

public class FuncNode extends AbstractTreeNode implements SubSpecNode {
   public final String name;
   public final List<ParamNode> params;
   public final String ret;

   public FuncNode(String name, List<ParamNode> params, String ret) {
      this.name = name;
      this.params = params;
      this.ret = ret;
   }

   public void accept(Visitor v) { v.visit(this); }
}
