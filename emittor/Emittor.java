package emittor;

import symbols.types.*;

public class Emittor {
   private int label = 0;

   public Emittor(String name) { }

   public void emit(String text) {
      System.out.print(text);
   }

   public void emitPutLine(TypeDescriptor t, int num) {
      emit("\tgetstatic java/lang/System/out Ljava/io/PrintStream;\n");
      emitLoad(t, num);
      emit("\tinvokevirtual java/io/PrintStream/println(");
      emit(t);
      emit(")V");
   }

   public void emitPut(TypeDescriptor t, int num) {
      emit("\tgetstatic java/lang/System/out Ljava/io/PrintStream;\n");
      emitLoad(t, num);
      emit("\tinvokevirtual java/io/PrintStream/print(");
      emit(t);
      emit(")V");
   }

   public void emit(TypeDescriptor t) {
      if (t instanceof IntegerTypeDescriptor) {
         emit("I");
      } else if (t instanceof FloatTypeDescriptor) {
         emit("F");
      } else if (t instanceof CharacterTypeDescriptor) {
         emit("C");
      } else if (t instanceof BooleanTypeDescriptor) {
         emit("Z");
      } else if (t instanceof StringTypeDescriptor) {
         emit("Ljava/lang/String;");
      } else if (t instanceof VoidTypeDescriptor) {
         emit("V");
      }
   }

   public void emitNeg(TypeDescriptor t) {
      if (t instanceof FloatTypeDescriptor) {
         emit("\tfneg\n");
      } else if (t instanceof IntegerTypeDescriptor) {
         emit("\tineg\n");
      }
   }

   public void emitAbs(TypeDescriptor t) {
      emit("\tdup\n");
      if (t instanceof FloatTypeDescriptor) {
         emit("\tfconst_0\n");
         emit("\tfcmpg\n");
         emit("\tifge a"+label+"\n");
         emit("\tfneg\n");
      } else if (t instanceof IntegerTypeDescriptor) {
         emit("\tifge a"+label+"\n");
         emit("\tineg\n");
      }
      emit("\ta"+label+":\n");
      label++;
   }

   private void emitBoolOp(String op) {
      emit("\t"+op+" b"+label+"\n");
      emit("\ticonst_0\n");
      emit("\tgoto end_b"+label+"\n");
      emit("\tb"+label+":\n");
      emit("\ticonst_1\n");
      emit("\tend_b"+label+":\n");
      label++;
   }

   public void emitRem() {
      emit("\tirem\n");
   }

   public void emitEQ(TypeDescriptor t) {
      if (!(t instanceof StringTypeDescriptor)) {
         if (t instanceof IntegerTypeDescriptor) {
            emit("\tisub\n");
         } else if (t instanceof FloatTypeDescriptor) {
            emit("\tfsub\n");
         } else if (t instanceof CharacterTypeDescriptor) {
            emit("\tisub\n");
         } else if (t instanceof BooleanTypeDescriptor) {
            emit("\tisub\n");
         }
         emitBoolOp("ifeq");
      } else {
         emit("\tinvokevirtual java/lang/String/equals(Ljava/lang/Object;)Z\n");
         emitBoolOp("ifne");
      }
   }

   public void emitNE(TypeDescriptor t) {
      if (!(t instanceof StringTypeDescriptor)) {
         if (t instanceof IntegerTypeDescriptor) {
            emit("\tisub\n");
         } else if (t instanceof FloatTypeDescriptor) {
            emit("\tfsub\n");
         } else if (t instanceof CharacterTypeDescriptor) {
            emit("\tisub\n");
         } else if (t instanceof BooleanTypeDescriptor) {
            emit("\tisub\n");
         } 
         emitBoolOp("ifne");
      } else {
         emit("\tinvokevirtual java/lang/String/equals(Ljava/lang/Object;)Z\n");
         emitBoolOp("ifeq");
      }
   }

   public void emitLE(TypeDescriptor t) {
      if (t instanceof IntegerTypeDescriptor) {
         emit("\tisub\n");
      } else if (t instanceof FloatTypeDescriptor) {
         emit("\tfcmpg\n");
      } else if (t instanceof CharacterTypeDescriptor) {
         emit("\tisub\n");
      } 
      emitBoolOp("ifle");
   }

   public void emitLT(TypeDescriptor t) {
      if (t instanceof IntegerTypeDescriptor) {
         emit("\tisub\n");
      } else if (t instanceof FloatTypeDescriptor) {
         emit("\tfcmpg\n");
      } else if (t instanceof CharacterTypeDescriptor) {
         emit("\tisub\n");
      } 
      emitBoolOp("iflt");
   }

   public void emitGE(TypeDescriptor t) {
      if (t instanceof IntegerTypeDescriptor) {
         emit("\tisub\n");
      } else if (t instanceof FloatTypeDescriptor) {
         emit("\tfcmpg\n");
      } else if (t instanceof CharacterTypeDescriptor) {
         emit("\tisub\n");
      } 
      emitBoolOp("ifge");
   }

   public void emitGT(TypeDescriptor t) {
      if (t instanceof IntegerTypeDescriptor) {
         emit("\tisub\n");
      } else if (t instanceof FloatTypeDescriptor) {
         emit("\tfcmpg\n");
      } else if (t instanceof CharacterTypeDescriptor) {
         emit("\tisub\n");
      } 
      emitBoolOp("ifgt");
   }

   public void emitAdd(TypeDescriptor t) {
      if (t instanceof IntegerTypeDescriptor) {
         emit("\tiadd\n");
      } else if (t instanceof FloatTypeDescriptor) {
         emit("\tfadd\n");
      }
   }

   public void emitSub(TypeDescriptor t) {
      if (t instanceof IntegerTypeDescriptor) {
         emit("\tisub\n");
      } else if (t instanceof FloatTypeDescriptor) {
         emit("\tfsub\n");
      }
   }

   public void emitMul(TypeDescriptor t) {
      if (t instanceof IntegerTypeDescriptor) {
         emit("\timul\n");
      } else if (t instanceof FloatTypeDescriptor) {
         emit("\tfmul\n");
      }
   }

   public void emitDiv(TypeDescriptor t) {
      if (t instanceof IntegerTypeDescriptor) {
         emit("\tidiv\n");
      } else if (t instanceof FloatTypeDescriptor) {
         emit("\tfdiv\n");
      }
   }

   public void emitLoad(TypeDescriptor t, int num) {
      if (t instanceof IntegerTypeDescriptor) {
         emit("\tiload "+num+"\n");
      } else if (t instanceof BooleanTypeDescriptor) {
         emit("\tiload "+num+"\n");
      } else if (t instanceof FloatTypeDescriptor) {
         emit("\tfload "+num+"\n");
      } else {
         emit("\taload "+num+"\n");
      }
   }

   public void emitStore(TypeDescriptor t, int num) {
      if (t instanceof IntegerTypeDescriptor) {
         emit("\tistore "+num+"\n");
      } else if (t instanceof BooleanTypeDescriptor) {
         emit("\tistore "+num+"\n");
      } else if (t instanceof FloatTypeDescriptor) {
         emit("\tfstore "+num+"\n");
      } else {
         emit("\tastore "+num+"\n");
      }
   }
}
