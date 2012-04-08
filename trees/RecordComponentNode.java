package trees;

import visitors.Visitor;
import java.util.List;

public class RecordComponentNode extends AbstractTreeNode implements RecordItemNode {
   public final List<IdNode> names;
   public final TypeNode type;
   public final ExprNode init;

   public RecordComponentNode(List<IdNode> n, TypeNode t, ExprNode i) {
      names = n;
      type = t;
      init = i;
   }

   public RecordComponentNode(List<IdNode> n, TypeNode t) {
      this(n, t, null);
   }

   public void accept(Visitor v) { v.visit(this); }
}
