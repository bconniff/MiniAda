package trees;

import visitors.Visitor;
import java.util.List;

public class ParamNode extends AbstractTreeNode {
   public final List<String> names;
   public final Mode m;
   public final TypeNode type;

   public static enum Mode {
      IN, OUT, IN_OUT;
   }

   public ParamNode(List<String> names, Mode m, TypeNode type) {
      this.names = names;
      this.m = m;
      this.type = type;
   }

   public void accept(Visitor v) { v.visit(this); }
}
