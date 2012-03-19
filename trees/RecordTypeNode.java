package trees;

import visitors.Visitor;
public class RecordTypeNode extends AbstractTreeNode implements TypeNode {
   public final RecordComponentListNode comps;

   public RecordTypeNode(RecordComponentListNode comps) {
      this.comps = comps;
   }

   public void accept(Visitor v) { v.visit(this); }
}
