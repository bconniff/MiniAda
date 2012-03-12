package trees;

import java.util.List;

public class ComponentNode {
   public ComponentNode(ExprNode expr) {}
   public ComponentNode(List<ComponentChoiceNode> choices, ExprNode expr) {}
   public ComponentNode(List<String> choices, TypeNode expr) {}

   public void setInit(ExprNode expr) {}
}
