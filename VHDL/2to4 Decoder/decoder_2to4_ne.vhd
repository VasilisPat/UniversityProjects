LIBRARY ieee;
USE ieee.std_logic_1164.ALL;

ENTITY dec_2to4_ne IS
PORT(
  a: IN std_logic_vector(2 DOWNTO 1);
  d: OUT std_logic_vector(4 DOWNTO 1));
END dec_2to4_ne;

ARCHITECTURE behav OF dec_2to4_ne IS
  BEGIN
    WITH a SELECT
      d <= "0001" WHEN "00",
      "0010" WHEN "01",
      "0100" WHEN "10",
      "1000" WHEN "11",
      "0000" WHEN OTHERS;
END behav;