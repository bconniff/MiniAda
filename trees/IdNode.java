package trees;

import visitors.Visitor;
import java.util.List;

public class IdNode extends AbstractTreeNode implements ExprNode {
   public static int count = 0;

   public final String id;
   public final int num;

   public IdNode(String n) {
      id = n;
      num = count++;
   }

}
