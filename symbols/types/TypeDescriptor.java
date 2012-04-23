package symbols.types;

import trees.*;

public abstract class TypeDescriptor {
   public boolean isAssignable(TypeDescriptor td) {
      return equals(td);
   }

   public boolean isError() {
      return this instanceof ErrorTypeDescriptor;
   }

   public TypeDescriptor applySuffix(SuffixNode s) {
      return new ErrorTypeDescriptor();
   }

   public String toString() {
      return this.getClass().getSimpleName();
   }
}
