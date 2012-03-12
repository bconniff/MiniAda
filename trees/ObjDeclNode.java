package trees;

import java.util.List;

public class ObjDeclNode implements DeclNode {
   public ObjDeclNode() {}

   public void setConst(boolean con) {}
   public void setDecl(List<String> names, TypeNode type) {}
   public void setInit(ExprNode expr) {}
}
