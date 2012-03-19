package trees;

import visitors.Visitor;
import java.util.List;

public class PkgSpecNode extends AbstractTreeNode implements CompilationNode {
   public final String name;
   public final List<PrivateItemNode> items;
   public final List<DeclNode> decls;

   public PkgSpecNode(String n, List<DeclNode> d, List<PrivateItemNode> i) {
      name = n;
      decls = d;
      items = i;
   }

   public void accept(Visitor v) { v.visit(this); }
}
