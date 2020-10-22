LIBRARY ieee;
USE ieee.std_logic_1164.ALL;

ENTITY ha IS 
PORT(
  A, B : IN bit;
  S, C : OUT bit);
END ha;

ARCHITECTURE behav of ha IS 
BEGIN 
  S<=(A XOR B); 
  C<=(A AND B); 
END behav; 