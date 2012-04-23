package symbols.types;

import java.util.LinkedHashMap;
import trees.*;

public class ProcTypeDescriptor extends TypeDescriptor {
   public final LinkedHashMap<String,TypeDescriptor> params;
   public final String name;

   public ProcTypeDescriptor(LinkedHashMap<String,TypeDescriptor> params, String name) {
      this.params = params;
      this.name = name;
   }

   public TypeDescriptor applySuffix(SuffixNode s) {
      if (s instanceof ParenSuffixNode)
         return new VoidTypeDescriptor();
      return new ErrorTypeDescriptor();
   }
}
