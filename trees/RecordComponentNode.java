package trees;

import visitors.Visitor;
import java.util.List;

public class RecordComponentNode extends AbstractTreeNode implements RecordItemNode {
   public final List<String> names;
   public final TypeNode type;
   public final ExprNode init;

   public RecordComponentNode(List<String> n, TypeNode t, ExprNode i) {
      names = n;
      type = t;
      init = i;
   }

   public RecordComponentNode(List<String> n, TypeNode t) {
      this(n, t, null);
   }

   public void accept(Visitor v) { v.visit(this); }
}
