package symbols.types;

import java.util.LinkedHashMap;
import trees.*;

public class FuncTypeDescriptor extends TypeDescriptor {
   public final LinkedHashMap<String,TypeDescriptor> params;
   public final String name;
   public final TypeDescriptor ret;

   public FuncTypeDescriptor(LinkedHashMap<String,TypeDescriptor> params, String name, TypeDescriptor ret) {
      this.params = params;
      this.name = name;
      this.ret = ret;
   }

   public TypeDescriptor applySuffix(SuffixNode s) {
      if (s instanceof ParenSuffixNode)
         return ret;
      return new ErrorTypeDescriptor();
   }
}
