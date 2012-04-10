package symbols;

import symbols.attributes.SymbolAttributes;
import trees.IdNode;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

public class SymbolTable {
   private LinkedList<Map<String,SymbolAttributes>> table;

   public SymbolTable() {
      table = new LinkedList<Map<String,SymbolAttributes>>();

      push();
   }

   public void push() {
      table.addFirst(new HashMap<String,SymbolAttributes>());
   }

   public void pop() {
      table.removeFirst();
   }

   public void add(String name, SymbolAttributes val) {
      table.getFirst().put(name, val);
   }

   public boolean isLocal(String name) {
      return table.getFirst().containsKey(name);
   }

   public boolean isLocal(IdNode id) {
      return isLocal(id.id);
   }

   public boolean isAssignable(String name) {
      return true; // XXX
   }

   public SymbolAttributes get(String name) {
      for (Map<String,SymbolAttributes> m : table) {
         final SymbolAttributes tmp = m.get(name);
         if (tmp != null)
            return tmp;
      }
      return null;
   }

   public String toString() {
      String result = "";

      for (Map<String,SymbolAttributes> m: table) {
         result += "Scope:\n";
         for (String s: m.keySet()) {
            result += "   " + s + "\n";
         }
      }

      return result;
   }
}
