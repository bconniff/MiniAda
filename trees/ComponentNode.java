package trees;

import java.util.List;

public class ComponentNode extends AbstractTreeNode {
   public final List<ComponentChoiceNode> choices;
   public final ExprNode expr;

   public ComponentNode(ExprNode e) {
      this(null, e);
   }

   public ComponentNode(List<ComponentChoiceNode> c, ExprNode e) {
      choices = c;
      expr = e;
   }

   public void accept(Visitor v) { v.visit(this); }
}
