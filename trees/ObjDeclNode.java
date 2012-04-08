package trees;

import visitors.Visitor;
import java.util.List;

public class ObjDeclNode extends AbstractTreeNode implements DeclNode {
   public final boolean con;
   public final List<IdNode> names;
   public final TypeNode type;
   public final ExprNode init;

   public ObjDeclNode(boolean c, List<IdNode> n, TypeNode t, ExprNode i) {
      con = c;
      names = n;
      type = t;
      init = i;
   }

   public ObjDeclNode(boolean c, List<IdNode> n, TypeNode t) {
      this(c, n, t, null);
   }

   public void accept(Visitor v) { v.visit(this); }
}
