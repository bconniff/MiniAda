package trees;

import visitors.Visitor;
import java.util.List;

public class BlockStmtNode extends AbstractTreeNode implements StmtNode {
   public final String name;
   public final List<DeclNode> decls;
   public final List<StmtNode> stmts;
   public final List<ExceptionHandlerNode> exceps;

   public BlockStmtNode(String n, List<DeclNode> d, List<StmtNode> s, List<ExceptionHandlerNode> e) {
      name = n;
      decls = d;
      stmts = s;
      exceps = e;
   }

   public BlockStmtNode(List<DeclNode> d, List<StmtNode> s, List<ExceptionHandlerNode> e) {
      this(null, d, s, e);
   }

   public void accept(Visitor v) { v.visit(this); }
}
