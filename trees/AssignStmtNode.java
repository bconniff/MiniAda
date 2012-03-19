package trees;

import visitors.Visitor;
public class AssignStmtNode extends AbstractTreeNode implements StmtNode {
   public final NameNode name;
   public final ExprNode expr;

   public AssignStmtNode(NameNode name, ExprNode expr) {
      this.name = name;
      this.expr = expr;
   }

   public void accept(Visitor v) { v.visit(this); }
}
