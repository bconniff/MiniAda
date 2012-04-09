package symbols.types;

import symbols.attributes.EnumAttributes;
import java.util.List;
import java.util.ArrayList;

public class EnumTypeDescriptor extends TypeDescriptor {
   public final List<EnumAttributes> items;

   public EnumTypeDescriptor() {
      this.items = new ArrayList<EnumAttributes>();
   }

   public void add(EnumAttributes ea) {
      items.add(ea);
   }
}
