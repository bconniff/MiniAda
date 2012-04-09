package trees;

import visitors.Visitor;

public class CallStmtNode extends AbstractTreeNode implements StmtNode {
   public final NameNode name;

   public CallStmtNode(NameNode name) {
      this.name = name;
   }

}
