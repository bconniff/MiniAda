package symbols;

import java.util.Map;
import java.util.HashMap;

public class SymbolTable {
   private final static int BUF_SIZE = 64;
   private final static int BUF_FACT = 2;
   private Map<String,Symbol>[] table;
   private int size = 0;

   public class ScopeUnderflowException extends Exception { }
   public class RedefinitionException extends Exception { }

   @SuppressWarnings("unchecked")
   public SymbolTable() {
      table = new Map[BUF_SIZE];

      push();
   }

   @SuppressWarnings("unchecked")
   public void push() {
      if (size == table.length) {
         Map<String,Symbol>[] tmp = new Map[table.length*BUF_FACT];
         for (int i = 0; i < table.length; i++)
            tmp[i] = table[i];
         table = tmp;
      }

      table[size++] = new HashMap<String,Symbol>();
   }

   public void pop()
   throws ScopeUnderflowException {
      if (size > 1)
         table[--size] = null;
      else throw new ScopeUnderflowException();
   }

   public void add(String name, Symbol val)
   throws RedefinitionException {
      if (!isLocal(name))
         table[size-1].put(name, val);
      else throw new RedefinitionException();
   }

   public boolean isLocal(String name) {
      return table[size-1].containsKey(name);
   }

   public Symbol get(String name) {
      for (int i = size - 1; i >= 0; i--) {
         Symbol tmp = table[i].get(name);
         if (tmp != null)
            return tmp;
      }
      return null;
   }

   public String toString() {
      String result = "";

      for (int i = 0; i < 0; i++) {
         result += "Scope #" + i + "\n";
         result += table[i].toString() + "\n";
      }

      return result;
   }
}
