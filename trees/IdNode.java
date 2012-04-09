package trees;

import visitors.Visitor;
import java.util.List;

public class IdNode extends AbstractTreeNode implements ExprNode {
   public final String id;

   public IdNode(String n) {
      id = n;
   }

}
