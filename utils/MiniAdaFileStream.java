package utils;

import java.io.IOException;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CharStream;

public class MiniAdaFileStream extends ANTLRFileStream {
   public MiniAdaFileStream(String s) throws IOException {
      super(s, null);
   }

   public MiniAdaFileStream(String s, String enc) throws IOException {
      super(s, enc);
   }

   public int LA(int i) {
      if (i == 0) return 0;
      if (i < 0) i++;

      return ((p + i - 1) >= n)
         ? CharStream.EOF
         : Character.toLowerCase(data[p+i-1]);
   }
}
