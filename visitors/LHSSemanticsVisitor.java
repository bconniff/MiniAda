package visitors;

import trees.*;
import symbols.*;
import symbols.types.*;

public class LHSSemanticsVisitor extends SemanticsVisitor {
   private final SymbolTable syms;

   public LHSSemanticsVisitor() {
      this(new SymbolTable());
   }

   public LHSSemanticsVisitor(SymbolTable s) {
      syms = s;
   }

   public void visit(IdNode n) {
      n.accept(new SemanticsVisitor(syms));

      if (!syms.isAssignable(n.id))
         error("Can't assign to "+n.id);
   }
}
