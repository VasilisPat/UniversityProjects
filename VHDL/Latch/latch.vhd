LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY latch IS PORT(
  D : IN BIT;
  EN : IN BIT;
  Q : OUT BIT);
END latch;

ARCHITECTURE behav OF latch IS
BEGIN
  PROCESS(D,EN)
  BEGIN
    IF EN='1' THEN
      Q <= D;
    END IF;
  END PROCESS;
END behav;