package trees;

import visitors.Visitor;
import java.util.List;

public class PragmaNode extends AbstractTreeNode implements DirecNode,StmtNode,DeclNode {
   public final String name;
   public final List<ArgNode> args;

   public PragmaNode(String n, List<ArgNode> a) {
      name = n;
      args = a;
   }

   public PragmaNode(String n) {
      this(n, null);
   }

   public void accept(Visitor v) { v.visit(this); }
}
