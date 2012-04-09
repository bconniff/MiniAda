package symbols.types;

public class FloatTypeDescriptor extends TypeDescriptor {
   public boolean equals(Object o) {
      return o instanceof FloatTypeDescriptor;
   }

   public String toString() {
      return "Float";
   }
}
