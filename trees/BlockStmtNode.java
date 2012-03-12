package trees;

import java.util.List;

public class BlockStmtNode implements StmtNode {
   public BlockStmtNode() {}

   public void setName(String name) {}
   public void setDecls(List<DeclNode> decls) {}
   public void setStmts(List<StmtNode> stmts) {}
   public void setExceps(List<ExceptionHandlerNode> exceps) {}
}
