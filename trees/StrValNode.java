package trees;

import visitors.Visitor;

public class StrValNode extends AbstractTreeNode implements ValNode {
   public final String val;

   public StrValNode(String s) {
      String tmp = s.substring(1, s.length()-1);

      // replace escaped quotes
      int c = tmp.indexOf("\"\"");
      while (c >= 0) {
         tmp = tmp.substring(0,c) + tmp.substring(c+1);
         c = tmp.indexOf("\"\"");
      }

      val = tmp;
   }

   public void accept(Visitor v) { v.visit(this); }
}
