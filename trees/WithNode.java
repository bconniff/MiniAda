package trees;

import java.util.List;
import java.util.ArrayList;

public class WithNode extends AbstractTreeNode implements DirecNode {
   private final List<List<String>> libs = new ArrayList<List<String>>();

   public WithNode() {}

   public void addLib(List<String> lib) {
      libs.add(lib);
   }
}
