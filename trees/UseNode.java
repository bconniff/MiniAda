package trees;

import java.util.List;
import java.util.ArrayList;

public class UseNode extends AbstractTreeNode implements DirecNode,DeclNode {
   private List<NameNode> names;
   private List<List<String>> libs = new ArrayList<List<String>>();

   public UseNode() { }

   public UseNode(List<NameNode> names) {
      this.names = names;
   }

   public void addLib(List<String> lib) {
      libs.add(lib);
   }
}
