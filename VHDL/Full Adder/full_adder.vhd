LIBRARY ieee;
USE ieee.std_logic_1164.ALL;

ENTITY fa IS 
PORT(
  A, B, Cin : IN bit;
  S, Cout : OUT bit);
END fa;

ARCHITECTURE behav OF fa IS 
BEGIN 
  S<=((A XOR B) XOR Cin); 
  Cout<=((A AND B) OR (B AND Cin) OR (Cin AND A)); 
END behav; 