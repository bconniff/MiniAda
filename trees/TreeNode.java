package trees;

import visitors.Visitor;
import symbols.*;

public interface TreeNode {
   public void accept(Visitor v);
   public SymbolAttributes getAttr();
   public TypeDescriptor getType();
   public void setAttr(SymbolAttributes sa);
   public void setType(TypeDescriptor td);
}
