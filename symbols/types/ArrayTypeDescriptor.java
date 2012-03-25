package symbols.types;

import symbols.TypeDescriptor;

public class ArrayTypeDescriptor implements TypeDescriptor {
   public final ArrayBounds bounds;
   public final TypeDescriptor elementType;

   public ArrayTypeDescriptor(ArrayBounds b, TypeDescriptor t) {
      bounds = b;
      elementType = t;
   }
}
