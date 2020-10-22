LIBRARY ieee;
USE ieee.std_logic_1164.ALL;

ENTITY triple_mux2to1 IS
PORT (
  a, b: IN std_logic_vector(2 DOWNTO 0);
     s: IN std_logic;
     d: OUT std_logic_vector(2 DOWNTO 0));
END triple_mux2to1;

ARCHITECTURE behav OF triple_mux2to1 IS
BEGIN
  d(2) <= a(2) WHEN s='1' ELSE b(2);
  d(1) <= a(1) WHEN s='1' ELSE b(1);
  d(0) <= a(0) WHEN s='1' ELSE b(0);
END behav;