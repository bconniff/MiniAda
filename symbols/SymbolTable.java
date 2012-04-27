package symbols;

import symbols.attributes.*;
import trees.IdNode;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

public class SymbolTable {
   private LinkedList<Map<String,SymbolAttributes>> table;
   private LinkedList<Map<String,Integer>> nums;
   private LinkedList<Integer> lastNum;
   private int currentNum = 0;

   public SymbolTable() {
      table = new LinkedList<Map<String,SymbolAttributes>>();
      nums = new LinkedList<Map<String,Integer>>();
      lastNum = new LinkedList<Integer>();

      push();
   }

   public void push() {
      table.addFirst(new HashMap<String,SymbolAttributes>());
      nums.addFirst(new HashMap<String,Integer>());
      lastNum.addFirst(currentNum);
   }

   public void pop() {
      table.removeFirst();
      nums.removeFirst();
      currentNum = lastNum.removeFirst();
   }

   public void add(String name, SymbolAttributes val) {
      table.getFirst().put(name, val);
      nums.getFirst().put(name, (val instanceof VariableAttributes ? currentNum++ : -1));
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

   public int getNum(String name) {
      for (Map<String,Integer> m : nums) {
         final Integer tmp = m.get(name);
         if (tmp != null)
            return tmp;
      }
      return -1;
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
