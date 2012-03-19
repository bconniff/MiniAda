package trees;

import visitors.Visitor;
import java.util.List;

public class LoopStmtNode extends AbstractTreeNode implements StmtNode {
   public final String name;
   public final LoopClauseNode loop;
   public final List<StmtNode> stmts;

   public LoopStmtNode(String n, LoopClauseNode l, List<StmtNode> s) {
      name = n;
      loop = l;
      stmts = s;
   }

   public LoopStmtNode(LoopClauseNode l, List<StmtNode> s) {
      this(null, l, s);
   }

   public LoopStmtNode(List<StmtNode> s) {
      this(null, new WhileClauseNode(new BoolValNode(true)), s);
   }

   public void accept(Visitor v) { v.visit(this); }
}
