package trees;

import visitors.Visitor;
import utils.BaseConv;

public class FloatValNode extends AbstractTreeNode implements ValNode {
   public final double val;

   public FloatValNode(double v) {
      val = v;
   }

   public FloatValNode(String s) {
      val = BaseConv.toDouble(s);
   }

   public void accept(Visitor v) { v.visit(this); }
}
