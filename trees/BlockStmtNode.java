package trees;

import visitors.Visitor;
import java.util.List;

public class BlockStmtNode extends AbstractTreeNode implements StmtNode {
   public final IdNode name;
   public final List<DeclNode> decls;
   public final List<StmtNode> stmts;
   public final List<ExceptionHandlerNode> exceps;

   public BlockStmtNode(IdNode n, List<DeclNode> d, List<StmtNode> s, List<ExceptionHandlerNode> e) {
      name = n;
      decls = d;
      stmts = s;
      exceps = e;
   }

   public BlockStmtNode(List<DeclNode> d, List<StmtNode> s, List<ExceptionHandlerNode> e) {
      this(null, d, s, e);
   }

}
