package trees;

import java.util.List;
import java.util.ArrayList;

public class PkgSpecNode extends AbstractTreeNode implements CompilationNode {
   private final String name;
   private final List<PrivateItemNode> items = new ArrayList<PrivateItemNode>();
   private final List<DeclNode> decls = new ArrayList<DeclNode>();

   public PkgSpecNode(String name) {
      this.name = name;
   }

   public void addItem(PrivateItemNode item) {
      items.add(item);
   }

   public void addDecl(DeclNode decl) {
      decls.add(decl);
   }
}
