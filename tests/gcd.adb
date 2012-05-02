procedure gcd is
   a: Integer := 252;
   b: Integer := 105;
   result: Integer := 1;
   tmp: Integer;
begin
   while b /= 0 loop
      tmp := b;
      b := a mod b;
      a := tmp;
   end loop;

   result := a;

   Put_Line(result);
end gcd;
