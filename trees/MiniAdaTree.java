package trees;

import visitors.Visitor;
import java.util.List;

public class MiniAdaTree extends AbstractTreeNode {
   public final List<DirecNode> direcs;
   public final List<CompilationNode> comps;

   public MiniAdaTree(List<DirecNode> d, List<CompilationNode> c) {
      direcs = d;
      comps = c;
   }
}
