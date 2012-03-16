package trees;

import java.util.List;

public class BlockStmtNode extends AbstractTreeNode implements StmtNode {
   private String name;
   private List<DeclNode> decls;
   private List<StmtNode> stmts;
   private List<ExceptionHandlerNode> exceps;

   public BlockStmtNode() {}

   public void setName(String name) {
      this.name = name;
   }

   public void setDecls(List<DeclNode> decls) {
      this.decls = decls;
   }

   public void setStmts(List<StmtNode> stmts) {
      this.stmts = stmts;
   }

   public void setExceps(List<ExceptionHandlerNode> exceps) {
      this.exceps = exceps;
   }
}
