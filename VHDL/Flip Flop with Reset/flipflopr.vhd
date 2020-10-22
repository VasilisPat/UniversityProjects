LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY flipflopr IS PORT(
  D : IN BIT;
  CLK : IN BIT;
  Rstn: IN BIT;
  Q : OUT BIT);
END flipflopr;

ARCHITECTURE behav OF flipflopr IS
BEGIN
  PROCESS(CLK,Rstn)
    BEGIN
      IF(Rstn='0') THEN
        Q<='0';
      ELSIF(CLK'EVENT AND CLK='1') THEN
        Q<=D;
      END IF;
  END PROCESS;
END behav;