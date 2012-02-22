procedure Tick_Test is
   type Card is record
      value: Character;
      suit: String(1..5);
   end record;

   c: Card := Card'('a', "Spade");
   d: Character := 'a';
begin
   for x in Integer'range loop
      null;
   end loop;
end Tick_Test;
