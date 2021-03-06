package symbols.types;

public class IdTypeDescriptor extends TypeDescriptor {
   public final String id;
   public final TypeDescriptor type;

   public IdTypeDescriptor(String id, TypeDescriptor type) {
      this.id = id;
      this.type = type;
   }

   public String toString() {
      return id;
   }
}
