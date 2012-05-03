procedure street is
	m, n : Integer;
	count : Integer;
	j : Integer := 8;
begin
	count := 0;
	while count < 6 loop
		for i in 1 .. j loop
			if 2*i*i = j*j+j then
				count := count + 1;
				Put_Line(i);
				Put_Line(j);
			end if;
		end loop;
		j := j + 1;
	end loop;
end street;
