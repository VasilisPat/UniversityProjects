LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY flipflop IS PORT(
  D : IN BIT;
  CLK : IN BIT;
  Q : OUT BIT);
END flipflop;

ARCHITECTURE behav OF flipflop IS
BEGIN
  PROCESS(CLK)
    BEGIN
      IF (CLK'EVENT AND CLK='1') THEN
        Q<=D;
      END IF;
  END PROCESS;
END behav; 