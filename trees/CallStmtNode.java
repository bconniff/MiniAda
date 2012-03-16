package trees;

public class CallStmtNode extends AbstractTreeNode implements StmtNode {
   private final NameNode name;

   public CallStmtNode(NameNode name) {
      this.name = name;
   }
}
