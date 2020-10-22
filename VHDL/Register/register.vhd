LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY mregister IS PORT(
  D : IN BIT_VECTOR(3 DOWNTO 0);
  clk : IN BIT;
  clr : IN BIT;
  Q : OUT BIT_VECTOR(3 DOWNTO 0));
END mregister;

ARCHITECTURE behav OF mregister IS
  BEGIN
  P3:PROCESS(clk,clr)
    BEGIN
      IF(clr='0') THEN
        Q(3)<='0';
      ELSIF(clk'EVENT AND clk='1') THEN
        Q(3)<=D(3);
      END IF;
  END PROCESS;
  
  P2:PROCESS(clk,clr)
    BEGIN
      IF(clr='0') THEN
        Q(2)<='0';
      ELSIF(clk'EVENT AND clk='1') THEN
        Q(2)<=D(2);
      END IF;
  END PROCESS;

  P1:PROCESS(clk,clr)
    BEGIN
      IF(clr='0') THEN
        Q(1)<='0';
      ELSIF(clk'EVENT AND clk='1') THEN
        Q(1)<=D(1);
      END IF;
  END PROCESS;
  
  P0:PROCESS(clk,clr)
    BEGIN
      IF(clr='0') THEN
        Q(0)<='0';
      ELSIF(clk'EVENT AND clk='1') THEN
        Q(0)<=D(0);
      END IF;
  END PROCESS;
END behav;