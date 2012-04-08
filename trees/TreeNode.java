package trees;

import visitors.Visitor;
import symbols.attributes.SymbolAttributes;
import symbols.types.TypeDescriptor;

public interface TreeNode {
   public void accept(Visitor v);
   public SymbolAttributes getAttr();
   public TypeDescriptor getType();
   public void setAttr(SymbolAttributes sa);
   public void setType(TypeDescriptor td);
}
