package symbols;

public class TypeAttributes implements SymbolAttributes {
   public final TypeDescriptor thisType;

   public TypeAttributes(TypeDescriptor type) {
      thisType = type;
   }
}
