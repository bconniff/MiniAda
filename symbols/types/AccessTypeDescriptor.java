package symbols.types;

public class AccessTypeDescriptor implements TypeDescriptor {
   public final TypeDescriptor accessType;

   public AccessTypeDescriptor(TypeDescriptor t) {
      accessType = t;
   }
}
