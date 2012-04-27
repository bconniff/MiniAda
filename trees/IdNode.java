package trees;

import visitors.Visitor;
import java.util.List;

public class IdNode extends AbstractTreeNode implements ExprNode {
   public final String id;
   public int num = -1;

   public IdNode(String n) {
      id = n;
   }

}
