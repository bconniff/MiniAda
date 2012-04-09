package symbols.types;

public class IncompleteTypeDescriptor extends TypeDescriptor {
   public final String id;

   public IncompleteTypeDescriptor(String id) {
      this.id = id;
   }

   public String toString() {
      return id;
   }
}
