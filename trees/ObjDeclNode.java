package trees;

import java.util.List;

public class ObjDeclNode extends AbstractTreeNode implements DeclNode {
   private boolean con = false;
   private List<String> names;
   private TypeNode type;
   private ExprNode init;

   public ObjDeclNode() {}

   public void setConst(boolean con) {
      this.con = con;
   }

   public void setDecl(List<String> names, TypeNode type) {
      this.names = names;
      this.type = type;
   }

   public void setInit(ExprNode expr) {
      init = expr;
   }
}
