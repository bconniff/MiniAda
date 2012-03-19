package trees;

import java.util.List;
import java.util.ArrayList;

public class SubDeclNode extends AbstractTreeNode implements DeclNode,CompilationNode {
   public final SubSpecNode spec;
   public final SubBodyNode body;

   public SubDeclNode(SubSpecNode s, SubBodyNode b) {
      spec = s;
      body = b;
   }

   public SubDeclNode(SubSpecNode s)  {
      this(s, null);
   }

   public void accept(Visitor v) { v.visit(this); }
}
