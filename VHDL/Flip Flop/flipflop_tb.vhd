LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY test_flipflop IS
END test_flipflop;

ARCHITECTURE test_b OF test_flipflop IS
SIGNAL D1,CLK1,Q1:bit;
COMPONENT flipflop PORT (D,CLK: IN bit; Q: OUT bit);
END COMPONENT;

BEGIN
 FF1:flipflop PORT MAP (D=>D1,CLK=>CLK1,Q=>Q1);
 PROCESS
  BEGIN
    --t1
    CLK1<='1';D1<='1';WAIT FOR 250 ns;
    CLK1<='0';WAIT FOR 100 ns;
    D1<='0';WAIT FOR 250 ns;
    --t2
    CLK1<='1';WAIT FOR 250 ns;
    CLK1<='0';WAIT FOR 100 ns;
    D1<='1';WAIT FOR 250 ns;
    --t3
    CLK1<='1';WAIT FOR 100 ns;
    D1<='0';WAIT FOR 150 ns;
    CLK1<='0';WAIT FOR 100 ns;
    D1<='1';WAIT FOR 50 ns;
    D1<='0';WAIT FOR 200 ns;
    --t4
    CLK1<='1';WAIT FOR 100 ns;
    D1<='1';WAIT FOR 150 ns;
    CLK1<='0';WAIT FOR 100 ns;
    D1<='0';WAIT FOR 250 ns;
 END PROCESS;
END test_b;