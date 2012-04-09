package trees;

import visitors.Visitor;
import java.util.List;

public class PragmaNode extends AbstractTreeNode implements DirecNode,StmtNode,DeclNode {
   public final IdNode name;
   public final List<ArgNode> args;

   public PragmaNode(IdNode n, List<ArgNode> a) {
      name = n;
      args = a;
   }

   public PragmaNode(IdNode n) {
      this(n, null);
   }

}
