package trees;

public class IntValNode extends AbstractTreeNode implements ValNode {
   private final String s;

   public IntValNode(String s) { 
      this.s = s;
   }
}
