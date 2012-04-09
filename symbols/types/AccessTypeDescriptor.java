package symbols.types;

public class AccessTypeDescriptor extends TypeDescriptor {
   public final TypeDescriptor accessType;

   public AccessTypeDescriptor(TypeDescriptor t) {
      accessType = t;
   }

   public String toString() {
      return "access "+accessType;
   }
}
