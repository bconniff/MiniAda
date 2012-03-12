package trees;

import java.util.List;

public class ParamNode {
   public static enum Mode {
      IN, OUT, IN_OUT;
   }

   public ParamNode(List<String> names, Mode m, TypeNode type) {}
}
