package symbols.types;

import symbols.TypeDescriptor;

public class IdTypeDescriptor implements TypeDescriptor {
   public final String id;

   public IdTypeDescriptor(String id) {
      this.id = id;
   }
}
