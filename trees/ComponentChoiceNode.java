package trees;

import visitors.Visitor;

public class ComponentChoiceNode extends AbstractTreeNode {
   public final ExprNode expr;
   public final RangeNode range;
   public final SubtypeNode type;

   public ComponentChoiceNode() {
      this.expr = null;
      this.range = null;
      this.type = null;
   }

   public ComponentChoiceNode(ExprNode expr) { 
      this.expr = expr;
      this.range = null;
      this.type = null;
   }

   public ComponentChoiceNode(RangeNode range) {
      this.expr = null;
      this.range = range;
      this.type = null;
   }

   public ComponentChoiceNode(SubtypeNode type) {
      this.expr = null;
      this.range = null;
      this.type = type;
   }

}
