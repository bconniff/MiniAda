package symbols.types;

import symbols.SymbolTable;

public class RecordTypeDescriptor implements TypeDescriptor {
   public final SymbolTable fields;

   public RecordTypeDescriptor(SymbolTable f) {
      fields = f;
   }
}
