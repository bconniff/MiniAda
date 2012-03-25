package symbols.types;

import symbols.TypeDescriptor;
import symbols.SymbolTable;

public class RecordTypeDescriptor implements TypeDescriptor {
   public final SymbolTable fields;

   public RecordTypeDescriptor(SymbolTable f) {
      fields = f;
   }
}
