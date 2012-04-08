package visitors;

import trees.*;
import symbols.*;
import symbols.types.*;

public class TopDeclVisitor extends SemanticsVisitor {
   private final SymbolTable syms;

   public TopDeclVisitor() {
      this(new SymbolTable());
   }

   public TopDeclVisitor(SymbolTable s) {
      syms = s;
   }

   public void visitChildren(AbstractTreeNode n) {
      for (AbstractTreeNode child: n.getChildren())
         visit(child);
   }

   // Declaration Nodes
   public void visit(ObjDeclNode n) {
      n.type.accept(new TypeVisitor(syms));

      for (IdNode s: n.names) {
         if (syms.isLocal(s.id)) {
            error("Duplicate definition: " + s);
         } else if (n.type.getAttr() instanceof TypeAttributes) {
            TypeAttributes attr = (TypeAttributes)n.type.getAttr();

            s.setType(n.type.getType());
            s.setAttr(new VariableAttributes(attr.thisType));

            syms.add(s.id, s.getAttr());
         } else {
            error("Invalid internal format");
         }
      }
   }

   public void visit(TypeDeclNode n) {
      n.type.accept(new TypeVisitor(syms));

      if (syms.isLocal(n.name.id)) {
         error("Duplicate definition: " + n.name);
      } else {
         n.name.setType(n.type.getType());
         n.name.setAttr(n.type.getAttr());

         syms.add(n.name.id, n.name.getAttr());
      }
   }

   public void visit(PrivateTypeDeclNode n) {
      if (syms.isLocal(n.name.id)) {
         error("Duplicate definition: " + n.name);
      } else {
         n.name.setType(new PrivateTypeDescriptor(n.name.id));
         n.name.setAttr(new TypeAttributes(n.name.getType()));

         syms.add(n.name.id, n.name.getAttr());
      }
   }
   
   public void visit(SubDeclNode n) {
      n.spec.accept(new TypeVisitor(syms));
      n.body.accept(this);
   }

   public void visit(SubtypeDeclNode n) {
      n.type.accept(new TypeVisitor(syms));

      if (syms.isLocal(n.name.id)) {
         error("Duplicate definition: " + n.name);
      } else {
         n.name.setType(n.type.getType());
         n.name.setAttr(n.type.getAttr());

         syms.add(n.name.id, n.name.getAttr());
      }
   }
}