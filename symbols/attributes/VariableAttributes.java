package symbols.attributes;

import symbols.types.TypeDescriptor;

public class VariableAttributes implements SymbolAttributes {
   public final TypeDescriptor variableType;

   public VariableAttributes(TypeDescriptor type) {
      variableType = type;
   }
}
