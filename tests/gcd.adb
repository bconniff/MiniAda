-- finds the greatest common demonitor of two numbers ('a' and 'b')
procedure gcd is
   -- inputs for the program
   a: Integer := 252;
   b: Integer := 105;

   -- temporary value
   tmp: Integer;

   -- output
   result: Integer := 1;
begin
   while b /= 0 loop
      tmp := b;
      b := a mod b;
      a := tmp;
   end loop;

   result := a;

   Put_Line(result);
end gcd;
