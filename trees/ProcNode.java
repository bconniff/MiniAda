package trees;

import visitors.Visitor;
import java.util.List;

public class ProcNode extends AbstractTreeNode implements SubSpecNode {
   public final IdNode name;
   public final List<ParamNode> params;

   public ProcNode(IdNode name, List<ParamNode> params) {
      this.name = name;
      this.params = params;
   }

   public void accept(Visitor v) { v.visit(this); }
}
