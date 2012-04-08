package symbols.types;

import symbols.TypeDescriptor;

public class IncompleteTypeDescriptor implements TypeDescriptor {
   public final String id;

   public IncompleteTypeDescriptor(String id) {
      this.id = id;
   }
}
