package trees;

import java.util.List;
import java.util.ArrayList;

public class MiniAdaTree extends AbstractTreeNode {
   public final List<DirecNode> direcs;
   public final List<CompilationNode> comps;

   public MiniAdaTree(List<DirecNode> d, List<CompilationNode> c) {
      direcs = d;
      comps = c;
   }

   public void accept(Visitor v) { v.visit(this); }
}
