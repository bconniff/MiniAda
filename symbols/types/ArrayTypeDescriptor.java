package symbols.types;

public class ArrayTypeDescriptor implements TypeDescriptor {
   public final long upper, lower;
   public final TypeDescriptor elementType;

   public ArrayTypeDescriptor(long upper, long lower, TypeDescriptor t) {
      this.upper = upper;
      this.lower = lower;
      elementType = t;
   }
}
