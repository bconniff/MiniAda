package trees;

import java.util.List;

public class ParamNode extends AbstractTreeNode {
   private final List<String> names;
   private final Mode m;
   private final TypeNode type;

   public static enum Mode {
      IN, OUT, IN_OUT;
   }

   public ParamNode(List<String> names, Mode m, TypeNode type) {
      this.names = names;
      this.m = m;
      this.type = type;
   }
}
