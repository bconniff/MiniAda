import org.antlr.runtime.*;
import org.antlr.runtime.tree.Tree;

import java.io.IOException;
import trees.*;
import visitors.*;

import symbols.*;
import symbols.types.*;
import symbols.attributes.*;

public class Main {
   final static SymbolTable sym = new SymbolTable();

   static {
      sym.add("Integer", new TypeAttributes(new IntegerTypeDescriptor()));
      sym.add("String", new TypeAttributes(new StringTypeDescriptor()));
      sym.add("Boolean", new TypeAttributes(new BooleanTypeDescriptor()));
      sym.add("Float", new TypeAttributes(new FloatTypeDescriptor()));
      sym.add("Character", new TypeAttributes(new CharacterTypeDescriptor()));
   }

   public static void main(String[] args) throws IOException {
      if (args.length < 1) {
         System.err.println("Usage: java Main <file> ...");
         return;
      }

      for (int i = 0; i < args.length; i++) {
         if (i > 0) System.out.println();
         System.out.println("Abstract syntax tree: "+args[i]);
         System.out.println("-----------------------------------------");
         MiniAdaLexer lex = new MiniAdaLexer(new MiniAdaFileStream(args[i]));
         CommonTokenStream tokens = new CommonTokenStream(lex);
         MiniAdaParser parse = new MiniAdaParser(tokens);
         
         try {
            AbstractTreeNode tree = parse.compilation(); 
            tree.accept(new SemanticsVisitor(sym)); // yay!
            System.out.println(tree);
         } catch (RecognitionException e) {
            e.printStackTrace();
            return;
         }
      }
   }
}
