package visitors;

import trees.*;
import symbols.*;
import symbols.types.*;
import symbols.attributes.*;

public class LHSSemanticsVisitor extends SemanticsVisitor {
   public LHSSemanticsVisitor(SymbolTable s) {
      super(s);
   }

   public void visit(IdNode n) {
      n.accept(new SemanticsVisitor(syms));

      if (!syms.isAssignable(n.id))
         error("Can't assign to "+n.id);
   }
}
