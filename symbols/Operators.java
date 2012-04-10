package symbols;

import symbols.types.*;

public class Operators {
   private final static TypeDescriptor intType = new IntegerTypeDescriptor();
   private final static TypeDescriptor fltType = new FloatTypeDescriptor();
   private final static TypeDescriptor strType = new StringTypeDescriptor();
   private final static TypeDescriptor chrType = new CharacterTypeDescriptor();
   private final static TypeDescriptor boolType = new BooleanTypeDescriptor();
   private final static TypeDescriptor errType = new ErrorTypeDescriptor();

   // and, and then, or, or else, xor
   public static TypeDescriptor boolOp(TypeDescriptor a, TypeDescriptor b) {
      return (a.equals(boolType) && b.equals(boolType))
         ? boolType
         : errType;
   }

   // <, >, <=, >=
   public static TypeDescriptor compOp(TypeDescriptor a, TypeDescriptor b) {
      return ((a.equals(intType) && b.equals(intType))
            || (a.equals(fltType) && b.equals(fltType)))
         ? boolType
         : errType;
   }

   // =, /=
   public static TypeDescriptor equalOp(TypeDescriptor a, TypeDescriptor b) {
      return (a.equals(b))
         ? boolType
         : errType;
   }

   // +, -, *, /, **
   public static TypeDescriptor arithOp(TypeDescriptor a, TypeDescriptor b) {
      return ((a.equals(intType) && b.equals(intType))
            || (a.equals(fltType) && b.equals(fltType)))
         ? a
         : errType;
   }

   // mod, rem
   public static TypeDescriptor modOp(TypeDescriptor a, TypeDescriptor b) {
      return (a.equals(intType) && b.equals(intType))
         ? intType
         : errType;
   }

   // not
   public static TypeDescriptor unBoolOp(TypeDescriptor a) {
      return a.equals(boolType)
         ? boolType
         : errType;
   }

   // +, -, abs
   public static TypeDescriptor unArithOp(TypeDescriptor a) {
      return (a.equals(intType) || a.equals(fltType))
         ? a
         : errType;
   }

   // &
   public static TypeDescriptor concatOp(TypeDescriptor a, TypeDescriptor b) {
      if ((a.equals(strType) || a.equals(chrType)) && (b.equals(strType) || b.equals(chrType)))
         return strType;

      return errType;
   }
}
