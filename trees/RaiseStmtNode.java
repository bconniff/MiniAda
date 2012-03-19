package trees;

public class RaiseStmtNode extends AbstractTreeNode implements StmtNode {
   public final NameNode excep;

   public RaiseStmtNode(NameNode excep) {
      this.excep = excep;
   }

   public void accept(Visitor v) { v.visit(this); }
}
