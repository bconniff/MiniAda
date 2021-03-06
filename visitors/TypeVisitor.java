package visitors;

import trees.*;
import symbols.*;
import symbols.types.*;
import symbols.attributes.*;

import java.util.LinkedHashMap;

public class TypeVisitor extends TopDeclVisitor {
   public TypeVisitor(SymbolTable s) {
      super(s);
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

   public void visit(AccessTypeNode n) { 
      if (n.con != null || n.ranges != null)
         error("Constraints on access types are not supported (yet)");
      else {
         visit(n.name);

         TypeDescriptor td = new AccessTypeDescriptor(n.name.getType());

         n.setType(td);
         n.setAttr(new TypeAttributes(td));
      }
   }

   public void visit(EnumTypeNode n) {
      final EnumTypeDescriptor td = new EnumTypeDescriptor();

      int i = 0;
      for (IdNode name: n.names) {
         final EnumAttributes a = new EnumAttributes(td, i++);

         syms.add(name.id, a);

         name.setType(td);
         name.setAttr(a);
         name.num = syms.getNum(name.id);

         td.add(a);
      }

      n.setType(td);
      n.setAttr(new TypeAttributes(td));
   }

   public void visit(ConstrainedArrayTypeNode n) {
      visitChildren(n);

      TypeDescriptor td = n.type.getType();

      for (RangeNode r: n.ranges) {
         if (r.a instanceof IntValNode && r.b instanceof IntValNode) {
            final long upper = ((IntValNode)r.a).val;
            final long lower = ((IntValNode)r.b).val;

            td = new ArrayTypeDescriptor(((IntValNode)r.a).val, ((IntValNode)r.b).val, td);
         } else {
            error("Non-Integer array ranges are not supported (yet)");
         }
      }

      n.setType(td);
      n.setAttr(new TypeAttributes(td));
   }

   public void visit(UnconstrainedArrayTypeNode n) {
      error("Unconstrained arrays are not supported (yet)");
   }

   public void visit(SubtypeNode n) {
      error("Subtypes are not supported (yet)");
   }

   public void visit(RecordTypeNode n) {
      error("Records are not supported (yet)");
      /*
      LinkedHashMap<String,SymbolAttributes> map = new LinkedHashMap<String,SymbolAttributes>();

      for (RecordItemNode i: n.comps) {
         if (i instanceof RecordComponentNode) {
            final RecordComponentNode comp = (RecordComponentNode)i;

            comp.type.accept(new TypeVisitor(syms));

            for (IdNode name: comp.names) {
               if (map.containsKey(name.id)) {
                  error("Duplicate name in record: "+name.id);
               } else {
                  map.put(name.id, comp.type.getType());
               }
            }
         } else {
            error("Unsupported record component");
         }
      }
      */
   }

   public void visit(ProcNode n) {
      LinkedHashMap<String,TypeDescriptor> map = new LinkedHashMap<String,TypeDescriptor>();
      for (ParamNode p: n.params) {
         p.type.accept(this);

         for (IdNode id: p.names) {
            if (map.containsKey(id.id))
               error("Duplicate parameter " + id.id);
            else {
               map.put(id.id, p.type.getType());
               id.num = syms.getNum(id.id);
            }
         }
      }

      final TypeDescriptor td = new ProcTypeDescriptor(map, n.name.id);
      final SymbolAttributes a = new TypeAttributes(td);

      n.setType(td);
      n.setAttr(a);

      if (syms.isLocal(n.name.id))
         error("Name is already defined: "+n.name.id);
      else
         syms.add(n.name.id, a);
   }

   public void visit(FuncNode n) {
      LinkedHashMap<String,TypeDescriptor> map = new LinkedHashMap<String,TypeDescriptor>();
      for (ParamNode p: n.params) {
         p.type.accept(this);

         for (IdNode id: p.names) {
            if (map.containsKey(id.id))
               error("Duplicate parameter " + id.id);
            else {
               map.put(id.id, p.type.getType());
               id.num = syms.getNum(id.id);
            }
         }
      }

      n.ret.accept(this);

      final TypeDescriptor td = new FuncTypeDescriptor(map, n.name.id, n.ret.getType());
      final SymbolAttributes a = new TypeAttributes(td);

      n.setType(td);
      n.setAttr(a);

      if (syms.isLocal(n.name.id))
         error("Name is already defined: "+n.name.id);
      else
         syms.add(n.name.id, a);
   }
}
