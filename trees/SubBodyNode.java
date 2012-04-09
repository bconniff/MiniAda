package trees;

import visitors.Visitor;
import java.util.List;

public class SubBodyNode extends AbstractTreeNode {
   public final List<DeclNode> decls;
   public final List<StmtNode> stmts;
   public final List<ExceptionHandlerNode> exceps;

   public SubBodyNode(List<DeclNode> d, List<StmtNode> s, List<ExceptionHandlerNode> e) {
      decls = d;
      stmts = s;
      exceps = e;
   }

}
