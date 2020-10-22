LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY test_latch IS
END test_latch;

ARCHITECTURE test_b OF test_latch IS
SIGNAL D1,EN1,Q1:bit;
COMPONENT latch PORT (D,EN: IN bit; Q: OUT bit);
END COMPONENT;

BEGIN
 L1:latch PORT MAP (D=>D1,EN=>EN1,Q=>Q1);
 PROCESS
  BEGIN
   --t1
   EN1<='1';D1<='1';WAIT FOR 100 ns;
   EN1<='0';WAIT FOR 50 ns;
   D1<='0';WAIT FOR 250 ns;
   --t2
   EN1<='1';WAIT FOR 100 ns;
   EN1<='0';WAIT FOR 50 ns;
   D1<='1';WAIT FOR 250 ns;
   --t3
   EN1<='1';WAIT FOR 50 ns;
   D1<='0'; WAIT FOR 50 ns;
   EN1<='0';WAIT FOR 50 ns;
   D1<='1'; WAIT FOR 100 ns;
   D1<='0'; WAIT FOR 150 ns;
   --t4
   EN1<='1';WAIT FOR 50 ns;
   D1<='1';WAIT FOR 50 ns;
   EN1<='0';WAIT FOR 100 ns;
  END PROCESS;
END test_b;