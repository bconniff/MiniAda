package trees;

public class RecordTypeNode extends AbstractTreeNode implements TypeNode {
   private final ComponentListNode comps;

   public RecordTypeNode(ComponentListNode comps) {
      this.comps = comps;
   }
}
