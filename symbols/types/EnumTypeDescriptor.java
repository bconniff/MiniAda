package symbols.types;

import symbols.TypeDescriptor;
import java.util.List;

public class EnumTypeDescriptor implements TypeDescriptor {
   public final List<EnumItem> items;

   public EnumTypeDescriptor(List<EnumItem> items) {
      this.items = items;
   }
}
