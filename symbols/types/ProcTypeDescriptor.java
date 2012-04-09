package symbols.types;

import symbols.attributes.SymbolAttributes;
import java.util.TreeMap;

public class ProcTypeDescriptor extends TypeDescriptor {
   public final TreeMap<String,SymbolAttributes> params;
   public final String name;

   public ProcTypeDescriptor(TreeMap<String,SymbolAttributes> params, String name) {
      this.params = params;
      this.name = name;
   }
}
