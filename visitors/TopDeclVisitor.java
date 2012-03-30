package visitors;

import trees.AbstractTreeNode;

public class TopDeclVisitor extends Visitor {"
   private final SymbolTable syms;

   public TopDeclVisitor() { }

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
      for (IdNode s: names) {
         if (syms.declaredLocally(s.name)) {
            error("Duplicate definition: " + s);
         } else if (n.type.symAttr instanceof TypeAttributes) {
            TypeAttributes attr = (TypeAttributes)n.type.symAttr;

            s.symType = n.type.symType;
            s.symAttr = new VariableAttributes(attr.thisType);

            syms.enterSymbol(s.name, s.symAttr);
         } else {
            error("Invalid internal format");
         }
      }
   }

   public void visit(TypeDeclNode n) {
      n.type.accept(new TypeVisitor(syms));

      if (syms.declaredLocally(s.name)) {
         error("Duplicate definition: " + s);
      } else {
         n.name.symType = n.type.symType;
         n.name.symAttr = n.type.symAttr;

         syms.enterSymbol(s.name, s.symAttr);
      }
   }
   
   public void visit(SubDeclNode n) { visitChildren(n); }
   public void visit(PrivateTypeDeclNode n) { visitChildren(n); }
   public void visit(SubtypeDeclNode n) { visitChildren(n); }
   
   // Other Nodes
   public void visit(AccessTypeNode n) { visitChildren(n); }
   public void visit(AggNode n) { visitChildren(n); }
   public void visit(AllSuffixNode n) { visitChildren(n); }
   public void visit(ArgNode n) { visitChildren(n); }
   public void visit(AssignStmtNode n) { visitChildren(n); }
   public void visit(AttrNode n) { visitChildren(n); }
   public void visit(AttrSuffixNode n) { visitChildren(n); }
   public void visit(BinNode n) { visitChildren(n); }
   public void visit(BlockStmtNode n) { visitChildren(n); }
   public void visit(BoolValNode n) { visitChildren(n); }
   public void visit(CallStmtNode n) { visitChildren(n); }
   public void visit(CaseStmtNode n) { visitChildren(n); }
   public void visit(CharValNode n) { visitChildren(n); }
   public void visit(ChoiceNode n) { visitChildren(n); }
   public void visit(ComponentChoiceNode n) { visitChildren(n); }
   public void visit(ComponentNode n) { visitChildren(n); }
   public void visit(ConstrainedArrayTypeNode n) { visitChildren(n); }
   public void visit(DotSuffixNode n) { visitChildren(n); }
   public void visit(EnumTypeNode n) { visitChildren(n); }
   public void visit(ExceptionDeclNode n) { visitChildren(n); }
   public void visit(ExceptionHandlerNode n) { visitChildren(n); }
   public void visit(ExitStmtNode n) { visitChildren(n); }
   public void visit(FloatValNode n) { visitChildren(n); }
   public void visit(ForClauseNode n) { visitChildren(n); }
   public void visit(FuncNode n) { visitChildren(n); }
   public void visit(IdTypeNode n) { visitChildren(n); }
   public void visit(IfClauseNode n) { visitChildren(n); }
   public void visit(IfStmtNode n) { visitChildren(n); }
   public void visit(IncompleteTypeNode n) { visitChildren(n); }
   public void visit(IndexConstraintNode n) { visitChildren(n); }
   public void visit(IntValNode n) { visitChildren(n); }
   public void visit(LoopStmtNode n) { visitChildren(n); }
   public void visit(MiniAdaTree n) { visitChildren(n); }
   public void visit(NameNode n) { visitChildren(n); }
   public void visit(NullStmtNode n) { visitChildren(n); }
   public void visit(ParamNode n) { visitChildren(n); }
   public void visit(ParenSuffixNode n) { visitChildren(n); }
   public void visit(PkgBodyNode n) { visitChildren(n); }
   public void visit(PkgSpecNode n) { visitChildren(n); }
   public void visit(PragmaNode n) { visitChildren(n); }
   public void visit(PrivateItemNode n) { visitChildren(n); }
   public void visit(ProcNode n) { visitChildren(n); }
   public void visit(RaiseStmtNode n) { visitChildren(n); }
   public void visit(RangeConstraintNode n) { visitChildren(n); }
   public void visit(RangeNode n) { visitChildren(n); }
   public void visit(RecordComponentListNode n) { visitChildren(n); }
   public void visit(RecordComponentNode n) { visitChildren(n); }
   public void visit(RecordTypeNode n) { visitChildren(n); }
   public void visit(ReturnStmtNode n) { visitChildren(n); }
   public void visit(StrValNode n) { visitChildren(n); }
   public void visit(SubBodyNode n) { visitChildren(n); }
   public void visit(SubtypeNode n) { visitChildren(n); }
   public void visit(UnaryNode n) { visitChildren(n); }
   public void visit(UnconstrainedArrayTypeNode n) { visitChildren(n); }
   public void visit(UseNode n) { visitChildren(n); }
   public void visit(VariantChoiceNode n) { visitChildren(n); }
   public void visit(VariantNode n) { visitChildren(n); }
   public void visit(WhenNode n) { visitChildren(n); }
   public void visit(WhileClauseNode n) { visitChildren(n); }
   public void visit(WithNode n) { visitChildren(n); }
}
