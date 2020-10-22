LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY test_reg8 IS
END test_reg8;

ARCHITECTURE test_b OF test_reg8 IS
  COMPONENT reg8 IS PORT (
      D: IN STD_LOGIC_VECTOR(3 DOWNTO 0);
      Resetn,Clock: IN STD_LOGIC; 
      Q: OUT STD_LOGIC_VECTOR(3 DOWNTO 0));
  END COMPONENT;
  
  SIGNAL D: STD_LOGIC_VECTOR(3 DOWNTO 0);
  SIGNAL Resetn,Clock: STD_LOGIC;
  SIGNAL Q: STD_LOGIC_VECTOR(3 DOWNTO 0);
  
  BEGIN
    R1: reg8 PORT MAP(D=>D,Resetn=>Resetn,Clock=>Clock,Q=>Q);
    PROCESS
      BEGIN
        D<="0010";Resetn<='0';Clock<='0'; WAIT FOR 50PS;
        D<="1110";Resetn<='1';Clock<='1'; WAIT FOR 50PS;
        D<="1010";Resetn<='1';Clock<='0'; WAIT FOR 50PS;
    END PROCESS;
END test_b;
  
  