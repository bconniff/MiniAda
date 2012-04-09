package trees;

import visitors.Visitor;
import java.util.List;

public class PkgSpecNode extends AbstractTreeNode implements CompilationNode {
   public final IdNode name;
   public final List<PrivateItemNode> items;
   public final List<DeclNode> decls;

   public PkgSpecNode(IdNode n, List<DeclNode> d, List<PrivateItemNode> i) {
      name = n;
      decls = d;
      items = i;
   }

}
