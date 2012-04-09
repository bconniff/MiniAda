package symbols.types;

public class CharacterTypeDescriptor extends TypeDescriptor {
   public boolean equals(Object o) {
      return o instanceof CharacterTypeDescriptor;
   }

   public String toString() {
      return "Character";
   }
}
