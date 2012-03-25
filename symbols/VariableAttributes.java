package symbols;

public class VariableAttributes implements SymbolAttributes {
   public final TypeDescriptor variableType;

   public VariableAttributes(TypeDescriptor type) {
      variableType = type;
   }
}
