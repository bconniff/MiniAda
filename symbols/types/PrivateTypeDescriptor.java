package symbols.types;

public class PrivateTypeDescriptor extends TypeDescriptor {
   public final String id;

   public PrivateTypeDescriptor(String id) {
      this.id = id;
   }

   public String toString() {
      return id;
   }
}
