package symbols.attributes;

import symbols.types.TypeDescriptor;

public class TypeAttributes implements SymbolAttributes {
   public final TypeDescriptor thisType;

   public TypeAttributes(TypeDescriptor type) {
      thisType = type;
   }
}
