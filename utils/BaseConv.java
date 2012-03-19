package utils;

// plugin to convert ada numbers into an internal representation as either
// an int or a double.
public class BaseConv {
   // converts a number from any base between 2 and 16 to base 10.
   private static class Converter {
      private final String n;
      private final int base;

      private Long dec = null;
      private Double frac = null;

      public Converter(String n, int base) {
         /*
         if (!(1 < base && base < 17))
            throw new RecognitionException();
         */

         this.n = n;
         this.base = base;
      }

      private int valOf(int i) {
         // ASCII magic
         int c = (int)n.charAt(i);
         if (48 <= c && c <= 57)
            c = c - 48;
         else if (97 <= c && c <= 122)
            c = c - 87;
         else if (65 <= c && c <= 90)
            c = c - 55;
         else c = -1;

         /*
         if (!(0 <= c && c < base))
            throw new RecognitionException();
         */

         return c;
      }

      // convert the integer part of a number
      public long toInt() {
         if (dec == null) {
            int d = 0;
            long val = 0;
            for (int i = (n.length() - 1); i >= 0; i--) {
               val += valOf(i) * Math.pow(base, d);
               d++;
            }

            dec = new Long(val);
         }

         return dec;
      }

      // convert the fractional part of a number
      public double toFrac() {
         if (frac == null) {
            int d = -1;
            double val = 0;
            for (int i = 0; i < n.length(); i++) {
               val += valOf(i) * (double)Math.pow(base, d);
               d--;
            }

            frac = new Double(val);
         }

         return frac;
      }
   }

   public static long toLong(String s) {
      final int sharp1, sharp2, base;

      sharp1 = s.indexOf("#");
      sharp2 = s.indexOf("#", sharp1 + 1);

      base = Integer.parseInt(s.substring(0, sharp1));

      return (new Converter(s.substring(sharp1+1, sharp2), base)).toInt();
   }

   public static double toDouble(String s) {
      final int sharp1, sharp2, dot, base;

      sharp1 = s.indexOf("#");
      dot = s.indexOf(".", sharp1 + 1);
      sharp2 = s.indexOf("#", dot + 1);

      base = Integer.parseInt(s.substring(0, sharp1));

      // convert the integer part
      final long i_val = (new Converter(s.substring(sharp1+1, dot), base).toInt());
      // convert the fractional part
      final double f_val = (new Converter(s.substring(dot+1, sharp2), base).toFrac());

      return (double)i_val + f_val;
   }
}
