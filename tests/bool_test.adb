procedure bool_test is
   a: Float := abs (0.0);
   b: Float := abs 3.0;
   c: Integer := abs (-9);
   d: Integer := abs 4;
   e: Integer := abs 0;
begin
   Put_Line(a);
   Put_Line(b);
   Put_Line(c);
   Put_Line(d);
   Put_Line(e);
end bool_test;
