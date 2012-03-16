package trees;

import java.util.List;
import java.util.ArrayList;

public class PkgBodyNode extends AbstractTreeNode implements CompilationNode {
   private final String name;
   private final List<DeclNode> decls = new ArrayList<DeclNode>();
   private List<StmtNode> stmts;
   private List<ExceptionHandlerNode> exceps;

   public PkgBodyNode(String name) {
      this.name = name;
   }

   public void addDecl(DeclNode decl) {
      decls.add(decl);
   }

   public void setStmts(List<StmtNode> stmts) {
      this.stmts = stmts;
   }

   public void setExceps(List<ExceptionHandlerNode> exceps) {
      this.exceps = exceps;
   }
}
