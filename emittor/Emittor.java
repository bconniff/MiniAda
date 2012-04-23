package emittor;

import symbols.types.*;

public class Emittor {
   public Emittor(String name) {
   }

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
      } else if (t instanceof BooleanTypeDescriptor) {
         emit("Z");
      } else if (t instanceof StringTypeDescriptor) {
         emit("Ljava/lang/String;");
      } else if (t instanceof VoidTypeDescriptor) {
         emit("V");
      }
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
      } else if (t instanceof FloatTypeDescriptor) {
         emit("\tfload "+num+"\n");
      } else {
         emit("\taload "+num+"\n");
      }
   }

   public void emitStore(TypeDescriptor t, int num) {
      if (t instanceof IntegerTypeDescriptor) {
         emit("\tistore "+num+"\n");
      } else if (t instanceof FloatTypeDescriptor) {
         emit("\tfstore "+num+"\n");
      } else {
         emit("\tastore "+num+"\n");
      }
   }
}
