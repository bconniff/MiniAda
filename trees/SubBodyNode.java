package trees;

import java.util.List;
import java.util.ArrayList;

public class SubBodyNode extends AbstractTreeNode {
   public final List<DeclNode> decls;
   public final List<StmtNode> stmts;
   public final List<ExceptionHandlerNode> exceps;

   public SubBodyNode(List<DeclNode> d, List<StmtNode> s, List<ExceptionHandlerNode> e) {
      decls = d;
      stmts = s;
      exceps = e;
   }

   public void accept(Visitor v) { v.visit(this); }
}
