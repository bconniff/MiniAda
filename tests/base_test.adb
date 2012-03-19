with Ada.Integer_Text_IO;
use Ada.Integer_Text_IO;

procedure Base_Test is
   a: Integer := 16#FF#e2;
   b: Float := 16#FF.1#;
   c: Float := 2#1011.11#e3;
begin
   a := 1e+4;
   b := 1.0e-4;
   Put(a);
end Base_Test;
