--with Ada.Integer_Text_IO;
--use Ada.Integer_Text_IO;

procedure Proc_Test is 
   a: Integer := 7;
begin
   -- Hi, I'm a comment!
   
   a := a + 3;
   Put(a);

   for x in 1 .. a loop
      Put(x);
   end loop;
end Proc_Test;

--procedure Proc_Test is
--begin
--   null;
--end Proc_Test;
