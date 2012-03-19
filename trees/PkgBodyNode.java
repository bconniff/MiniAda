package trees;

import java.util.List;
import java.util.ArrayList;

public class PkgBodyNode extends AbstractTreeNode implements CompilationNode {
   public final String name;
   public final List<DeclNode> decls;
   public final List<StmtNode> stmts;
   public final List<ExceptionHandlerNode> exceps;

   public PkgBodyNode(String n, List<DeclNode> d, List<StmtNode> s, List<ExceptionHandlerNode> e) {
      name = n;
      decls = d;
      stmts = s;
      exceps = e;
   }

   public void accept(Visitor v) { v.visit(this); }
}
