package trees;

public class NullStmtNode extends AbstractTreeNode implements StmtNode {
   public NullStmtNode() {}

   public void accept(Visitor v) { v.visit(this); }
}
