package trees;

public class ExitStmtNode extends AbstractTreeNode implements StmtNode {
   public final String id;
   public final ExprNode expr;

   public ExitStmtNode(String i, ExprNode e) {
      id = i;
      expr = e;
   }

   public ExitStmtNode(String i) {
      this(i, new BoolValNode(true));
   }

   public ExitStmtNode(ExprNode e) {
      this(null, e);
   }

   public ExitStmtNode() {
      this(null, new BoolValNode(true));
   }

   public void accept(Visitor v) { v.visit(this); }
}
