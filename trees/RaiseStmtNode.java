package trees;

import visitors.Visitor;

public class RaiseStmtNode extends AbstractTreeNode implements StmtNode {
   public final NameNode excep;

   public RaiseStmtNode(NameNode excep) {
      this.excep = excep;
   }

}
