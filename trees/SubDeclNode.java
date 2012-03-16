package trees;

import java.util.List;
import java.util.ArrayList;

public class SubDeclNode extends AbstractTreeNode implements DeclNode,CompilationNode {
   private final SubSpecNode spec;
   private final List<DeclNode> decls = new ArrayList<DeclNode>();
   private SubBodyNode body;


   public SubDeclNode(SubSpecNode spec) {
      this.spec = spec;
   }

   public void addDecl(DeclNode decl) {
      decls.add(decl);
   }

   public void setBody(SubBodyNode body) {
      this.body = body;
   }
}
