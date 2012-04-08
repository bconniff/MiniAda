package trees;

import visitors.Visitor;
import java.util.List;

public class FuncNode extends AbstractTreeNode implements SubSpecNode {
   public final IdNode name;
   public final List<ParamNode> params;
   public final IdNode ret;

   public FuncNode(IdNode name, List<ParamNode> params, IdNode ret) {
      this.name = name;
      this.params = params;
      this.ret = ret;
   }

   public void accept(Visitor v) { v.visit(this); }
}
