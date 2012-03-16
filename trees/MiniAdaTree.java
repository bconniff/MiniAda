package trees;

import java.util.List;
import java.util.ArrayList;

public class MiniAdaTree extends AbstractTreeNode {
   private final List<DirecNode> direcs = new ArrayList<DirecNode>();
   private final List<CompilationNode> comps = new ArrayList<CompilationNode>();

   public MiniAdaTree() {}

   public void addDirec(DirecNode direc) {
      direcs.add(direc);
   }

   public void addUnit(CompilationNode comp) {
      comps.add(comp);
   }
}
