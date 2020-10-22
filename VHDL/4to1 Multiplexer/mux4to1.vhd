LIBRARY ieee;
USE ieee.std_logic_1164.ALL;

ENTITY mux_four_to_one IS 
PORT( 
  a: IN std_logic_vector(4 DOWNTO 1);
  s: IN std_logic_vector(2 DOWNTO 1);
  d: OUT std_logic);
END mux_four_to_one;

ARCHITECTURE behav OF mux_four_to_one IS
  BEGIN
  WITH s SELECT
    d <= a(4) WHEN "00",
    a(3) WHEN "01",
    a(2) WHEN "10",
    a(1) WHEN OTHERS;
END behav;