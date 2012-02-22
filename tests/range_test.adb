procedure Range_Test is
   -- lexer must disambiguate from range
   a: Float := 3.9;
begin
   -- no spaces, lexer must disambiguate from FLOAT
   for x in 3..9 loop
      null;
   end loop;

   -- with spaces
   for x in 3 .. 9 loop
      null;
   end loop;
end Range_Test;
