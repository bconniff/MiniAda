import org.antlr.runtime.*;
import org.antlr.runtime.tree.Tree;

import java.io.File;
import java.io.IOException;

import utils.*;
import trees.*;
import visitors.*;
import grammar.*;
import symbols.*;
import symbols.types.*;
import symbols.attributes.*;

public class Main {
   private final static SymbolTable sym = new SymbolTable();

   static {
      sym.add("Integer", new TypeAttributes(new IntegerTypeDescriptor()));
      sym.add("String", new TypeAttributes(new StringTypeDescriptor()));
      sym.add("Boolean", new TypeAttributes(new BooleanTypeDescriptor()));
      sym.add("Float", new TypeAttributes(new FloatTypeDescriptor()));
      sym.add("Character", new TypeAttributes(new CharacterTypeDescriptor()));
   }

   public static void main(String[] args) throws IOException {
      final boolean showAst = option("ast", false);

      if (args.length < 1) {
         System.err.println("Usage: java Main <file> ...");
         return;
      }

      for (int i = 0; i < args.length; i++) {
         if (i > 0) System.out.println();
         MiniAdaLexer lex = new MiniAdaLexer(new MiniAdaFileStream(args[i]));
         CommonTokenStream tokens = new CommonTokenStream(lex);
         MiniAdaParser parse = new MiniAdaParser(tokens);

         final String basename = (new File(args[i])).getName();
         final String mainProc = basename.substring(0,basename.lastIndexOf('.'));
         
         try {
            AbstractTreeNode tree = parse.compilation(); 
            tree.accept(new SemanticsVisitor(sym)); // yay!
            tree.accept(new CodeGenVisitor(mainProc));

            if (showAst) {
               System.out.println("-----------------------------------------");
               System.out.println("Abstract syntax tree: "+args[i]);
               System.out.println("-----------------------------------------");
               System.out.println(new PrettyPrint(tree.toString()));
            }
         } catch (RecognitionException e) {
            e.printStackTrace();
            return;
         }
      }
   }

   private static boolean option(String key, boolean def) {
      final String val = System.getProperty(key);

      if (val != null) {
         if (val.equals("on"))
            return true;
         if (val.equals("off"))
            return false;
      }

      return def;
   }
}
