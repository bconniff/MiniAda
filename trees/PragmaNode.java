package trees;

import java.util.List;
import java.util.ArrayList;

public class PragmaNode extends AbstractTreeNode implements DirecNode,StmtNode,DeclNode {
   private final String name;
   private final List<PragmaArgNode> args = new ArrayList<PragmaArgNode>();

   public PragmaNode(String name) {
      this.name = name;
   }

   public void addArg(PragmaArgNode arg) {
      args.add(arg);
   }
}
