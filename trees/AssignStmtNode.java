package trees;

public class AssignStmtNode extends AbstractTreeNode implements StmtNode {
   private final NameNode name;
   private final ExprNode expr;

   public AssignStmtNode(NameNode name, ExprNode expr) {
      this.name = name;
      this.expr = expr;
   }
}
