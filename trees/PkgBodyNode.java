package trees;

import visitors.Visitor;
import java.util.List;

public class PkgBodyNode extends AbstractTreeNode implements CompilationNode {
   public final IdNode name;
   public final List<DeclNode> decls;
   public final List<StmtNode> stmts;
   public final List<ExceptionHandlerNode> exceps;

   public PkgBodyNode(IdNode n, List<DeclNode> d, List<StmtNode> s, List<ExceptionHandlerNode> e) {
      name = n;
      decls = d;
      stmts = s;
      exceps = e;
   }

   public void accept(Visitor v) { v.visit(this); }
}
