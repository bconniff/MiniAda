package symbols.types;

public class StringTypeDescriptor extends TypeDescriptor {
   public boolean equals(Object o) {
      return o instanceof StringTypeDescriptor;
   }

   public String toString() {
      return "String";
   }
}
