import org.antlr.runtime.*;
import org.antlr.runtime.tree.Tree;

import java.util.*;
import java.io.*;

import utils.*;
import trees.*;
import visitors.*;
import grammar.*;
import symbols.*;
import symbols.types.*;
import symbols.attributes.*;

public class Main {
   private final static SymbolTable sym = new SymbolTable();
   private final static LinkedHashMap<String,TypeDescriptor> putParams = new LinkedHashMap<String,TypeDescriptor>();

   static {
      putParams.put("item", new StringTypeDescriptor());

      sym.add("Integer", new TypeAttributes(new IntegerTypeDescriptor()));
      sym.add("String", new TypeAttributes(new StringTypeDescriptor()));
      sym.add("Boolean", new TypeAttributes(new BooleanTypeDescriptor()));
      sym.add("Float", new TypeAttributes(new FloatTypeDescriptor()));
      sym.add("Character", new TypeAttributes(new CharacterTypeDescriptor()));
      sym.add("Put_Line", new VariableAttributes(new ProcTypeDescriptor(putParams, "Put_Line")));
   }

   public static void main(String[] args) throws IOException {
      final boolean sysOut = option("asm", false);
      final boolean showAst = option("tree", false);

      if (args.length < 1) {
         System.err.println("Usage: java -jar miniada.jar <file> ...");
         System.err.println("Options:");
         System.err.println("   -Dasm=on   print generated assembly on (doesn't generate a class)");
         System.err.println("   -Dtree=on  display abstract syntax tree");
         System.err.println("MiniAda generates java class files, which can be run with the java program");
         return;
      }

      for (int i = 0; i < args.length; i++) {
         if (i > 0) System.out.println();
         MiniAdaLexer lex = new MiniAdaLexer(new MiniAdaFileStream(args[i]));
         CommonTokenStream tokens = new CommonTokenStream(lex);
         MiniAdaParser parse = new MiniAdaParser(tokens);

         final String basename = (new File(args[i])).getName();
         final String mainProc = basename.substring(0,basename.lastIndexOf('.'));
         final File jasFile = new File(mainProc+".j");
         final PrintStream oldOut = System.out;

         if (!sysOut && !showAst)
            System.setOut(new PrintStream(new FileOutputStream(jasFile)));
         
         try {
            TreeNode tree = parse.compilation(); 
            tree.accept(new SemanticsVisitor(sym)); // yay!

            if (showAst) {
               System.out.println("-----------------------------------------");
               System.out.println("Abstract syntax tree: "+args[i]);
               System.out.println("-----------------------------------------");
               System.out.println(new PrettyPrint(tree.toString()));
            } else {
               tree.accept(new CodeGenVisitor(mainProc));
            }
         } catch (RecognitionException e) {
            e.printStackTrace();
            return;
         }

         if (!sysOut && !showAst) {
            System.setOut(oldOut);
            (new jasmin.Main()).assemble(jasFile.toString());
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
