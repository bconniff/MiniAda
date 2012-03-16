package trees;

public class CharValNode extends AbstractTreeNode implements ValNode {
   private final String s;

   public CharValNode(String s) {
      this.s = s;
   }
}
