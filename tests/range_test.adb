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

   b := 3 = 3.0;

   -- with spaces
   for x in 3 .. 9 loop
      null;
   end loop;
end Range_Test;
