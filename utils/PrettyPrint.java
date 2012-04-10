package utils;

public class PrettyPrint {
   public final static int DEFAULT_TAB = 2;

   private final int tab;
   private final String text;

   public PrettyPrint(String text, int tab) {
      this.tab = tab;
      this.text = prettify(text);
   }

   public PrettyPrint(String text) {
      this(text, DEFAULT_TAB);
   }

   private String prettify(String s) {
      String result = "", append = "";

      int indent = 0;
      boolean skip = false;

      for (int i = 0; i < s.length(); i++) {
         final char in = s.charAt(i);

         switch (in) {
            case '[':
            case '{':
               append = (skip ? "\n" + strMult(" ",indent) : "") + Character.toString(in);
               indent += tab;
               skip = true;
               break;

            case ']':
            case '}':
               indent -= tab;
               append = (skip ? "" : "\n" + strMult(" ", indent)) + in;
               skip = false;
               break;

            case ',':
               append = Character.toString(in);
               skip = true;
               break;

            case ' ':
               append = (skip ? "" : Character.toString(in));
               break;
            
            default:
               append = (skip ? "\n" + strMult(" ", indent) : "") + Character.toString(in);
               skip = false;
               break;
         }

         result += append;
      }

      return result;
   }

   private static String strMult(String s, int count) {
      String result = "";

      for (int i = 0; i < count; i++)
         result += s;

      return result;
   }

   public String toString() {
      return text;
   }
}
