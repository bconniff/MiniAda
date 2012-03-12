package trees;

import java.util.List;

public class PkgBodyNode implements CompilationNode {
   public PkgBodyNode(String name) {}

   public void addDecl(DeclNode decl) {}
   public void setStmts(List<StmtNode> stmts) {}
   public void setExceps(List<ExceptionHandlerNode> exceps) {}
}
