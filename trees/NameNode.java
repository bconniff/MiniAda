package trees;

import java.util.List;
import java.util.ArrayList;

public class NameNode extends AbstractTreeNode implements ExprNode {
   private final String s;
   private final List<SuffixNode> suffs = new ArrayList<SuffixNode>();

   public NameNode(String s) {
      this.s = s;
   }

   public void addSuffix(SuffixNode suff) {
      suffs.add(suff);
   }
}
