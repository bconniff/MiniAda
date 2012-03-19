package trees;

import java.util.List;
import java.util.ArrayList;

public class RecordComponentListNode extends AbstractTreeNode {
   public final List<RecordComponentNode> comps;
   public final VariantNode var;

   public RecordComponentListNode(List<RecordComponentNode> c, VariantNode v) {
      comps = c;
      var = v;
   }

   public RecordComponentListNode(List<RecordComponentNode> c) {
      this(c, null);
   }

   public RecordComponentListNode() {
      this(null, null);
   }

   public void accept(Visitor v) { v.visit(this); }
}
