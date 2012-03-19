package trees;

public class CallStmtNode extends AbstractTreeNode implements StmtNode {
   public final NameNode name;

   public CallStmtNode(NameNode name) {
      this.name = name;
   }

   public void accept(Visitor v) { v.visit(this); }
}
