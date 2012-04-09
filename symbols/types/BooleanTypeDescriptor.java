package symbols.types;

public class BooleanTypeDescriptor extends TypeDescriptor {
   public boolean equals(Object o) {
      return o instanceof BooleanTypeDescriptor;
   }

   public String toString() {
      return "Boolean";
   }
}
