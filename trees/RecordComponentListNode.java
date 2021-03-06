package trees;

import visitors.Visitor;
import java.util.List;

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

}
