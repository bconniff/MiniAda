package symbols.types;

public abstract class TypeDescriptor {
   public boolean isAssignable(TypeDescriptor td) {
      return equals(td);
   }

   public String toString() {
      return this.getClass().getSimpleName();
   }
}
