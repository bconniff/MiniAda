package symbols.attributes;

import symbols.types.EnumTypeDescriptor;

public class EnumAttributes implements SymbolAttributes {
   public final EnumTypeDescriptor td;
   public final int val;

   public EnumAttributes(EnumTypeDescriptor td, int val) {
      this.td = td;
      this.val = val;
   }
}
