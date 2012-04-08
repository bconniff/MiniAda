package symbols.types;

import symbols.TypeDescriptor;

public class IdTypeDescriptor implements TypeDescriptor {
   public final String id;
   public final TypeDescriptor type;

   public IdTypeDescriptor(String id, TypeDescriptor type) {
      this.id = id;
      this.type = type;
   }
}
