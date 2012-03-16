package trees;

import java.util.List;

public class ComponentNode extends AbstractTreeNode {
   private final List<ComponentChoiceNode> choices;
   private final List<String> names;
   private final TypeNode type;
   private final ExprNode expr;
   private ExprNode init;

   public ComponentNode(ExprNode expr) {
      choices = null;
      names = null;
      type = null;
      this.expr = expr;
   }

   public ComponentNode(List<ComponentChoiceNode> choices, ExprNode expr) {
      names = null;
      type = null;
      this.choices = choices;
      this.expr = expr;
   }

   public ComponentNode(List<String> names, TypeNode type) {
      choices = null;
      expr = null;
      this.names = names;
      this.type = type;
   }

   public void setInit(ExprNode expr) {
      this.init = expr;
   }
}
