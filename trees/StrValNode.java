package trees;

public class StrValNode extends AbstractTreeNode implements ValNode {
   private final String s;

   public StrValNode(String s) {
      this.s = s;
   }
}
