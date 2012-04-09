package trees;

import visitors.Visitor;
import utils.BaseConv;

public class IntValNode extends AbstractTreeNode implements ValNode {
   public final long val;

   public IntValNode(long n) {
      val = n;
   }

   public IntValNode(String s) {
      val = BaseConv.toLong(s);
   }

}
