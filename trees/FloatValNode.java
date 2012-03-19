package trees;

import visitors.Visitor;
import utils.BaseConv;

public class FloatValNode extends AbstractTreeNode implements ValNode {
   public final double val;

   public FloatValNode(double v) {
      val = v;
   }

   public FloatValNode(String s, boolean conv) {
      val = (conv ? BaseConv.toDouble(s) : Double.parseDouble(s));
   }

   public FloatValNode(String s) {
      this(s, false);
   }

   public void accept(Visitor v) { v.visit(this); }
}
