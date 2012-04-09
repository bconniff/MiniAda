package visitors;

import trees.*;

public class EmptyVisitor extends Visitor {
   public EmptyVisitor() { }

   public void visitChildren(AbstractTreeNode n) {
      for (AbstractTreeNode child: n.getChildren()) {
         System.out.println("Visiting: " + child);
         child.accept(this);
      }
   }

   public void visit(AccessTypeNode n) { visitChildren(n); }
   public void visit(AggNode n) { visitChildren(n); }
   public void visit(AllSuffixNode n) { visitChildren(n); }
   public void visit(ArgNode n) { visitChildren(n); }
   public void visit(AssignStmtNode n) { visitChildren(n); }
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
   public void visit(IdNode n) { visitChildren(n); }
   public void visit(IdTypeNode n) { visitChildren(n); }
   public void visit(IfClauseNode n) { visitChildren(n); }
   public void visit(IfStmtNode n) { visitChildren(n); }
   public void visit(IncompleteTypeNode n) { visitChildren(n); }
   public void visit(IndexConstraintNode n) { visitChildren(n); }
   public void visit(IntValNode n) { visitChildren(n); }
   public void visit(LoopStmtNode n) { visitChildren(n); }
   public void visit(MiniAdaTree n) {
      System.out.println("Hi");
      visitChildren(n);
   }
   public void visit(NameNode n) { visitChildren(n); }
   public void visit(NullStmtNode n) { visitChildren(n); }
   public void visit(ObjDeclNode n) { visitChildren(n); }
   public void visit(ParamNode n) { visitChildren(n); }
   public void visit(ParenSuffixNode n) { visitChildren(n); }
   public void visit(PkgBodyNode n) { visitChildren(n); }
   public void visit(PkgSpecNode n) { visitChildren(n); }
   public void visit(PragmaNode n) { visitChildren(n); }
   public void visit(PrivateItemNode n) { visitChildren(n); }
   public void visit(PrivateTypeDeclNode n) { visitChildren(n); }
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
   public void visit(SubDeclNode n) { visitChildren(n); }
   public void visit(SubtypeDeclNode n) { visitChildren(n); }
   public void visit(SubtypeNode n) { visitChildren(n); }
   public void visit(TypeDeclNode n) { visitChildren(n); }
   public void visit(UnaryNode n) { visitChildren(n); }
   public void visit(UnconstrainedArrayTypeNode n) { visitChildren(n); }
   public void visit(UseNode n) { visitChildren(n); }
   public void visit(VariantChoiceNode n) { visitChildren(n); }
   public void visit(VariantNode n) { visitChildren(n); }
   public void visit(WhenNode n) { visitChildren(n); }
   public void visit(WhileClauseNode n) { visitChildren(n); }
   public void visit(WithNode n) { visitChildren(n); }
}
