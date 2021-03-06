package trees;

import visitors.Visitor;
import java.util.List;

public class FuncNode extends AbstractTreeNode implements SubSpecNode {
   public final IdNode name;
   public final List<ParamNode> params;
   public final TypeNode ret;

   public FuncNode(IdNode name, List<ParamNode> params, TypeNode ret) {
      this.name = name;
      this.params = params;
      this.ret = ret;
   }

}
