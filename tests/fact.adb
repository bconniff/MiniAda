procedure fact is
   input: Integer := 8;
   result: Integer := 1;
begin
   for i in 2 .. input loop
      result := result * i;
   end loop;

   Put_Line(result);
end fact;
