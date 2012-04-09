package symbols.types;

import symbols.attributes.SymbolAttributes;
import java.util.TreeMap;

public class FuncTypeDescriptor extends TypeDescriptor {
   public final TreeMap<String,SymbolAttributes> params;
   public final String name;
   public final SymbolAttributes ret;

   public FuncTypeDescriptor(TreeMap<String,SymbolAttributes> params, String name, SymbolAttributes ret) {
      this.params = params;
      this.name = name;
      this.ret = ret;
   }
}
