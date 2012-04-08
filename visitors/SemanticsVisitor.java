package visitors;

import symbols.*;
import symbols.types.*;
import trees.AbstractTreeNode;

public class SemanticsVisitor extends Visitor {
   private final SymbolTable syms;

   public SemanticsVisitor(SymbolTable syms) { this.syms = syms; }
   public SemanticsVisitor() { this(new SymbolTable()); }

   public void visitChildren(AbstractTreeNode n) {
      for (AbstractTreeNode child: n.getChildren())
         visit(child);
   }
}
