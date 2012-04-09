package symbols.types;

public class IntegerTypeDescriptor extends TypeDescriptor {
   public boolean equals(Object o) {
      return o instanceof IntegerTypeDescriptor;
   }

   public String toString() {
      return "Integer";
   }
}
