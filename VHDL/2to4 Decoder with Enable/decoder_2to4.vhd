LIBRARY ieee;
USE ieee.std_logic_1164.ALL;

ENTITY dec_2to4 IS
PORT(
  a: IN std_logic_vector(2 DOWNTO 1);
  en: IN std_logic;
  d: OUT std_logic_vector(4 DOWNTO 1));
END dec_2to4;

ARCHITECTURE behav OF dec_2to4 IS
  SIGNAL ena: std_logic_vector(3 DOWNTO 1);
  BEGIN
    ena <= en & a;
    WITH ena SELECT
      d <= "0001" WHEN "100",
      "0010" WHEN "101",
      "0100" WHEN "110",
      "1000" WHEN "111",
      "0000" WHEN OTHERS;
END behav;