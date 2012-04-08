package visitors;

import trees.*;
import symbols.*;
import symbols.types.*;

public class TypeVisitor extends TopDeclVisitor {
   private final SymbolTable syms;

   public TypeVisitor() {
      this(new SymbolTable());
   }

   public TypeVisitor(SymbolTable s) {
      syms = s;
   }

   public void visitChildren(AbstractTreeNode n) {
      for (AbstractTreeNode child: n.getChildren())
         visit(child);
   }

   // Type nodes
   public void visit(IdTypeNode n) {
      final SymbolAttributes a = syms.get(n.name.id);

      if (a instanceof TypeAttributes) {
         final TypeDescriptor td = ((TypeAttributes)a).thisType;

         n.setType(td);
         n.setAttr(a);
      } else
         error(n.name.id + " is not a type!");
   }


   public void visit(IncompleteTypeNode n) {
      final TypeDescriptor td = new IncompleteTypeDescriptor(n.name.id);

      n.setType(td);
      n.setAttr(new TypeAttributes(td));
   }

   public void visit(AccessTypeNode n) { visitChildren(n); }
   public void visit(ConstrainedArrayTypeNode n) { visitChildren(n); }
   public void visit(UnconstrainedArrayTypeNode n) { visitChildren(n); }
   public void visit(SubtypeNode n) { visitChildren(n); }
   public void visit(RecordTypeNode n) { visitChildren(n); }
   public void visit(EnumTypeNode n) { visitChildren(n); }
}
