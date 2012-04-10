package trees;

import visitors.Visitor;
import java.util.List;

public class MiniAdaTree extends AbstractTreeNode {
   public final List<DirecNode> direcs;
   public final CompilationNode comp;

   public MiniAdaTree(List<DirecNode> d, CompilationNode c) {
      direcs = d;
      comp = c;
   }
}
