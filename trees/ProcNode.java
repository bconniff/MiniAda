package trees;

import java.util.List;

public class ProcNode extends AbstractTreeNode implements SubSpecNode {
   public final String name;
   public final List<ParamNode> params;

   public ProcNode(String name, List<ParamNode> params) {
      this.name = name;
      this.params = params;
   }

   public void accept(Visitor v) { v.visit(this); }
}
