with Ada.Text_IO; use Ada.Text_IO;

procedure Range_Test is
   -- lexer must disambiguate from range
   a: Float := 3.9 + 4.7;
   b: Boolean := 3.9 + 4.7 > 4.0;
begin
   null;
   -- no spaces, lexer must disambiguate from FLOAT
   for x in 3..9 loop
      null;
   end loop;

   b := (3.0 = 3.0);
   --Put_Line(b);

   -- with spaces
   for x in 3 .. 9 loop
      null;
   end loop;

   for y in reverse 0 .. -1 loop
	   Put_Line(y'img);
   end loop;

   for z in 10 .. 15 loop
	   Put_Line(z'img);
   end loop;
end Range_Test;
