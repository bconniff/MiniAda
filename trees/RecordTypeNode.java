package trees;

import visitors.Visitor;
import java.util.List;

public class RecordTypeNode extends AbstractTreeNode implements TypeNode {
   public final List<RecordItemNode> comps;

   public RecordTypeNode(List<RecordItemNode> comps) {
      this.comps = comps;
   }

   public void accept(Visitor v) { v.visit(this); }
}
