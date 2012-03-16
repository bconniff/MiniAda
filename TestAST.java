import org.antlr.runtime.*;
import org.antlr.runtime.tree.Tree;
import org.antlr.runtime.debug.ParseTreeBuilder;

public class TestAST {
   public static void main(String[] args) throws Exception {
      if (args.length < 1) {
         System.err.println("Usage: java Test <file> ...");
         return;
      }

      for (int i = 0; i < args.length; i++) {
         MiniAdaLexer lex = new MiniAdaLexer(new MiniAdaFileStream(args[i]));
         CommonTokenStream tokens = new CommonTokenStream(lex);
         ParseTreeBuilder builder = new ParseTreeBuilder("compilation");
         MiniAdaParser parse = new MiniAdaParser(tokens, builder);
         
         try {
            System.out.println(parse.compilation());
         } catch (RecognitionException e) {
            e.printStackTrace();
            return;
         }
      }
   }
}
