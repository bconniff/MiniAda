package symbols;

public class Symbol {
   public static enum SymbolKind {
      FUNC, PROC, VAR, TYPE, SUBTYPE;
   }

   public final String name;
   public final SymbolAttributes attrs;

   public Symbol(String n, SymbolAttributes a) {
      this.name = n;
      this.attrs = a;
   }
}
