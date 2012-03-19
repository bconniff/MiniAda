package trees;

import visitors.Visitor;
import utils.BaseConv;

public class IntValNode extends AbstractTreeNode implements ValNode {
   public final long val;

   public IntValNode(long n) {
      val = n;
   }

   public IntValNode(String s, boolean conv) {
      val = (conv ? BaseConv.toLong(s) : Long.parseLong(s));
   }

   public IntValNode(String s) {
      this(s, false);
   }

   public void accept(Visitor v) { v.visit(this); }
}
