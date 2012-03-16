package trees;

public class RaiseStmtNode extends AbstractTreeNode implements StmtNode {
   private final NameNode excep;

   public RaiseStmtNode(NameNode excep) {
      this.excep = excep;
   }
}
